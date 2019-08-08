package com.instance.management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companydetails")
public class CompanyMetaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String companyname;
	
	int totalruns;
	
	int remainingruns;
	
	String token;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getTotalruns() {
		return totalruns;
	}
	public void setTotalruns(int totalruns) {
		this.totalruns = totalruns;
	}
	public int getRemainingruns() {
		return remainingruns;
	}
	public void setRemainingruns(int remainingruns) {
		this.remainingruns = remainingruns;
	}
	
}
