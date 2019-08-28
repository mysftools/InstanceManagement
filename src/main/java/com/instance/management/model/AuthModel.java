package com.instance.management.model;


public class AuthModel {

	String UserName;
	
	String Password;
	
	String EmpId;
	
	String Secret;
	
	int Number;
	
	String code;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmpId() {
		return EmpId;
	}

	public void setEmpId(String empId) {
		EmpId = empId;
	}

	public String getSecret() {
		return Secret;
	}

	public void setSecret(String secret) {
		Secret = secret;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
