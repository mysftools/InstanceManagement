package com.instance.management.model;

public class OrderModel {
	
	String key;
	String username;
	int amount;
	String orderid;
	String email;
	boolean status;
	String creationStatus;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getCreationStatus() {
		return creationStatus;
	}
	public void setCreationStatus(String creationStatus) {
		this.creationStatus = creationStatus;
	}
	
	
}
