package com.instance.management.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "backupdetails")
public class BackUpMetaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	String username;
	
	String nameOfInstance;
	
	String userid;
	String instanceid;
	Date backuptime =new Date(System.currentTimeMillis());
	
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
	public String getNameOfInstance() {
		return nameOfInstance;
	}
	public void setNameOfInstance(String nameOfInstance) {
		this.nameOfInstance = nameOfInstance;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getInstanceid() {
		return instanceid;
	}
	public void setInstanceid(String instanceid) {
		this.instanceid = instanceid;
	}
	public Date getBackuptime() {
		return backuptime;
	}
	public void setBackuptime(Date backuptime) {
		this.backuptime = backuptime;
	}

}
