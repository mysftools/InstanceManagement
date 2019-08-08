package com.instance.management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instanceInfo")
public class InstanceMetaModel {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	String securityCode;

	String coustomerName;

	boolean isSandbox;

	String token;

	String instToken;

	String nameOfInstance;

	String clientkey;

	String clientSecreat;

	String password;

	String username;
	
	String apiversion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getCoustomerName() {
		return coustomerName;
	}

	public void setCoustomerName(String coustomerName) {
		this.coustomerName = coustomerName;
	}

	public boolean isSandbox() {
		return isSandbox;
	}

	public void setSandbox(boolean isSandbox) {
		this.isSandbox = isSandbox;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNameOfInstance() {
		return nameOfInstance;
	}

	public void setNameOfInstance(String nameOfInstance) {
		this.nameOfInstance = nameOfInstance;
	}

	public String getInstToken() {
		return instToken;
	}

	public void setInstToken(String instToken) {
		this.instToken = instToken;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApiversion() {
		return apiversion;
	}

	public void setApiversion(String apiversion) {
		this.apiversion = apiversion;
	}
}
