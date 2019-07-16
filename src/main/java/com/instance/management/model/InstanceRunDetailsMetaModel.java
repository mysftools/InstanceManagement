package com.instance.management.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "instanceRunDetails")
@Entity
public class InstanceRunDetailsMetaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	String instToken;
	String detailToken;
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
	public String getDetailToken() {
		return detailToken;
	}
	public void setDetailToken(String detailToken) {
		this.detailToken = detailToken;
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
