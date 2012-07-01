package com.amc.caphm.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

public abstract class BaseDao extends NamedParameterJdbcDaoSupport{
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	void init(){
		setDataSource(dataSource);
	}
}