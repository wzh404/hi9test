package com.amc.caphm.tags;

import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.amc.caphm.annotation.Function;
import com.amc.caphm.model.User;

public class RoleTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4295316890555914917L;
	Logger logger = Logger.getLogger(RoleTag.class);
	
	private String uri;
	private String func;
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}
	
	private boolean checkUserFunction(String[] functions){
		User user = (User)pageContext.getSession().getAttribute("user");
		if (user == null){
			logger.info("session user is null");
			return false;
		}

		return user.checkActionFunctions(functions);
	}
	
	@Override
	public int doStartTag() {
		logger.info("start tag ok!");
		
		/*   check role */
		if (func != null){
			String[] fs = func.split(",");
			if (checkUserFunction(fs)){
				return EVAL_BODY_INCLUDE;
			}
			
			return SKIP_BODY;
		}
		
		if (checkUri()){
			return EVAL_BODY_INCLUDE;
		}
		
		return SKIP_BODY;
	}
	
	private boolean match(String pattern, String val){
		return false;
	}
	
	private boolean checkUri(){
		ApplicationContext ac = RequestContextUtils.getWebApplicationContext(pageContext.getRequest());
		RequestMappingHandlerMapping hm = ac.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo,HandlerMethod> map = hm.getHandlerMethods();

		for(Map.Entry<RequestMappingInfo,HandlerMethod> m: map.entrySet()){
			Set<String> set = m.getKey().getPatternsCondition().getPatterns();			
			if (set.isEmpty()){
				continue;
			}
			
			String[] s = set.toArray(new String[set.size()]);
			logger.info("key is " + s[0]);
			logger.info("value is "  + " - " + 
					m.getValue().getBeanType().getName()  + " - " + 
					m.getValue().getMethod().getName());
			if (match(s[0],uri)){
				Function func = m.getValue().getMethodAnnotation(Function.class);
				if (func == null){
					break;
				}

				for (String f : func.value()){
					logger.info("function is " + f);
				}	
				
				if (checkUserFunction(func.value())){
					return true;
				}
				
				break;
			}				
		}
		
		return false;
	}
}
