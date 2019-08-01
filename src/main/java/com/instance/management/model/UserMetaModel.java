package com.instance.management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usertable")
public class UserMetaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	String username;

	String userid;
	
	String token;

	String password;
	
	boolean Otpstatus;

	String companyName;
	
	String role;
	
	int attempt;

	boolean status;

	int calls;

	int remainingCalls;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isOtpstatus() {
		return Otpstatus;
	}

	public void setOtpstatus(boolean otpstatus) {
		Otpstatus = otpstatus;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCalls() {
		return calls;
	}

	public void setCalls(int calls) {
		this.calls = calls;
	}

	public int getRemainingCalls() {
		return remainingCalls;
	}

	public void setRemainingCalls(int remainingCalls) {
		this.remainingCalls = remainingCalls;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	
}
