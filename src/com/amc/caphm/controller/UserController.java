package com.amc.caphm.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amc.caphm.annotation.Function;
import com.amc.caphm.model.User;
import com.amc.caphm.service.UserService;

@Controller
@Function({"0100","0200"})
public class UserController {
	Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
	@Function({"0110"})
	public String rest(@PathVariable String name, Model model) {
		User user = userService.findByName(name);
		logger.info("----user is " + user);
		
		model.addAttribute("user", user);
		
		return "/view/test";
	}
	
	@RequestMapping(value = "/json/{name}", method = RequestMethod.GET)
	@Function({"0110"})
	public @ResponseBody User json(@PathVariable String name, Model model) {
		User user = userService.findByName(name);
		logger.info("----json is " + user);
		
		return user;
	}
}
