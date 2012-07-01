package com.amc.caphm.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.amc.caphm.annotation.Function;
import com.amc.caphm.model.User;

public class RoleInterceptor extends HandlerInterceptorAdapter{
	Logger logger = Logger.getLogger(RoleInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, 
            Object handler) throws Exception {
		if (handler instanceof HandlerMethod){				
			HandlerMethod  h = (HandlerMethod)handler;
			Method method = h.getMethod();
			
			if (method.isAnnotationPresent(Function.class)){
				String[] funcs = method.getAnnotation(Function.class).value();
				if (funcs == null)
					return false;
				
				User user = (User)request.getSession().getAttribute("user");
				if (user == null)
					return true; //test
				
				if (!user.checkActionFunctions(funcs)){
					return false;
				}
			}
			
			logger.info("Starting invoke role interceptor.");
		}
	
		return true;
	}
}
