package com.amc.caphm.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.amc.caphm.model.User;

public interface UserService {
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User findByName(String name);
}
