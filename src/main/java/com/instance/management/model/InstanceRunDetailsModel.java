package com.instance.management.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class InstanceRunDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	String instToken;
	Date date;
	String instname;
	String stript;
	int noOfCalls;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInstToken() {
		return instToken;
	}
	public void setInstToken(String instToken) {
		this.instToken = instToken;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getInstname() {
		return instname;
	}
	public void setInstname(String instname) {
		this.instname = instname;
	}
	public String getStript() {
		return stript;
	}
	public void setStript(String stript) {
		this.stript = stript;
	}
	public int getNoOfCalls() {
		return noOfCalls;
	}
	public void setNoOfCalls(int noOfCalls) {
		this.noOfCalls = noOfCalls;
	}
}
