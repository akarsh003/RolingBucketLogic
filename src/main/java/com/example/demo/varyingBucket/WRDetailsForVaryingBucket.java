package com.example.demo.varyingBucket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WRDetailsForVaryingBucket {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private int userId;
	private String workRequestType;
	private int workRequest;
	private boolean isQrSent; 
	private double qrPercent;
	private double bucketSize;
	
	public WRDetailsForVaryingBucket(int userId, String workRequestType, int workRequest, boolean isQrSent,
			double qrPercent,double bucketSize) {
		super();
		this.userId = userId;
		this.workRequestType = workRequestType;
		this.workRequest = workRequest;
		this.isQrSent = isQrSent;
		this.qrPercent = qrPercent;
		this.bucketSize = bucketSize;
	}
	public double getBucketSize() {
		return bucketSize;
	}
	public void setBucketSize(int bucketSize) {
		this.bucketSize = bucketSize;
	}
	public WRDetailsForVaryingBucket() {
//		super();
	}
	public WRDetailsForVaryingBucket(int userId, String workRequestType, int workRequest, boolean isQrSent,
			double qrPercent) {
//		super();
		this.userId = userId;
		this.workRequestType = workRequestType;
		this.workRequest = workRequest;
		this.isQrSent = isQrSent;
		this.qrPercent = qrPercent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getQrPercent() {
		return qrPercent;
	}
	public void setQrPercent(int qrPercent) {
		this.qrPercent = qrPercent;
	}
	
}
