package com.instance.management.model;

public class UserUpdateModel {
	
	String username;

	String token;

	String password;

	int attempt;
	
	String clientkey;

	String clientSecreat;
	
	boolean status;
	
	int calls;

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

	public String getClientkey() {
		return clientkey;
	}

	public void setClientkey(String clientkey) {
		this.clientkey = clientkey;
	}

	public String getClientSecreat() {
		return clientSecreat;
	}

	public void setClientSecreat(String clientSecreat) {
		this.clientSecreat = clientSecreat;
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


}
