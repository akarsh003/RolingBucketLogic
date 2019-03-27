package com.example.demo.rollingBucket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WorkRequestDetails {

	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private int userId;
	private String workRequestType;
	private int workRequest;
	private boolean isQrSent;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	public WorkRequestDetails(int userId, String workRequestType, int workRequest, boolean isQrSent) {
//		super();
		this.userId = userId;
		this.workRequestType = workRequestType;
		this.workRequest = workRequest;
		this.isQrSent = isQrSent;
	}
	public WorkRequestDetails() {
//		super();
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
	public int getWorkRequest() {
		return workRequest;
	}
	public void setWorkRequest(int workRequest) {
		this.workRequest = workRequest;
	}
	public boolean isQrSent() {
		return isQrSent;
	}
	public void setQrSent(boolean isQrSent) {
		this.isQrSent = isQrSent;
	} 
	
}
