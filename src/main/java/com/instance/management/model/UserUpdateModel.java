package com.instance.management.model;

public class UserUpdateModel {
	
	String username;
	
	String userid;
	
	String listInst;

	String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getListInst() {
		return listInst;
	}

	public void setListInst(String listInst) {
		this.listInst = listInst;
	}
}
