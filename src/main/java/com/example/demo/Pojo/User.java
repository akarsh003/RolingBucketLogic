package com.example.demo.Pojo;

import javax.persistence.Entity;
import javax.persistence.Id;


public class User {

	/*@Id
	private int id;*/
	private int userId;
	private String workRequestType;
	private String workRequest;
	
	public String getWorkRequest() {
		return workRequest;
	}
	public void setWorkRequest(String workRequest) {
		this.workRequest = workRequest;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getWorkRequestType() {
		return workRequestType;
	}
	public void setWorkRequestType(String workRequestType) {
		this.workRequestType = workRequestType;
	}
}
