package com.amc.caphm.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amc.caphm.dao.UserDao;
import com.amc.caphm.model.User;
import com.amc.caphm.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService{
	Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	UserDao userDao;
	
	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return userDao.findByName(name);
	}
}
