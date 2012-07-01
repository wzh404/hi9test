package com.amc.caphm.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.amc.caphm.model.User;

@Repository("UserDao")
public class UserDaoImpl extends BaseDao implements UserDao{	
	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select * from users where name = :name";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		User user = this.getNamedParameterJdbcTemplate().queryForObject(sql, map, new BeanPropertyRowMapper(User.class));
		
		return user;
	}
}