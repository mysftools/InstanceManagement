package com.instance.management.model;

public class EmailModel {
	
	private String email;
	private String emailpassword;
	private String host;
	private int port;
	
	private String token;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailpassword() {
		return emailpassword;
	}
	public void setEmailpassword(String emailpassword) {
		this.emailpassword = emailpassword;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
}

