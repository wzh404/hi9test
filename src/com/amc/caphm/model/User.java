package com.amc.caphm.model;

import javax.validation.constraints.Size;

public class User {
	private Integer id;
	
	@Size(min = 2, max = 6)
	private String name;
	
	private String passwd;
	
	private String pinyin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	public boolean checkActionFunctions(String[] actionFunctions){
		return true;
	}
}