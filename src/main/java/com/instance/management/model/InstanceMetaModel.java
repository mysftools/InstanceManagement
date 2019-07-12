package com.instance.management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "instanceInfo")
public class InstanceMetaModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	String securityCode;

	String type;

	String token;
	
	String instToken;

	String nameOfInstance;
	
	String clientkey;

	String clientSecreat;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
}
