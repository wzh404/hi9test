package com.amc.caphm.dao;

import com.amc.caphm.model.User;

public interface UserDao {
	public User findByName(String name);
}
