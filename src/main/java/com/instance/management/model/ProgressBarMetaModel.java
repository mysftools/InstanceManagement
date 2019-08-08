package com.instance.management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "progress")
@Entity
public class ProgressBarMetaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	String instrundtoken;
	
	String insttoken;

	float percentage;

	long threadId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getInstrundtoken() {
		return instrundtoken;
	}

	public void setInstrundtoken(String instrundtoken) {
		this.instrundtoken = instrundtoken;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public long getThreadId() {
		return threadId;
	}

	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}

	public String getInsttoken() {
		return insttoken;
	}

	public void setInsttoken(String insttoken) {
		this.insttoken = insttoken;
	}
	
}
