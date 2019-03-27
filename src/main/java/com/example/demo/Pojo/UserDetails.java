package com.example.demo.Pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetails {

	@Id
	private int userId;
	private String userName;
	private String workRequestType;
	private int qrPercent;
	private int bucketSize;
	
	
	public int getBucketSize() {
		return bucketSize;
	}
	public void setBucketSize(int bucketSize) {
		this.bucketSize = bucketSize;
	}
	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWorkRequestType() {
		return workRequestType;
	}
	public void setWorkRequestType(String workRequestType) {
		this.workRequestType = workRequestType;
	}
	public int getQrPercent() {
		return qrPercent;
	}
	public void setQrPercent(int qrPercent) {
		this.qrPercent = qrPercent;
	}
	
}
