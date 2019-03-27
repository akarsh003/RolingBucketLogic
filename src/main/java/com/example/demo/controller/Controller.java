package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Pojo.User;
import com.example.demo.Pojo.UserDetails;
import com.example.demo.Pojo.UserDetailsRepository;
import com.example.demo.rollingBucket.WokrRequestDetailsRepository;
import com.example.demo.rollingBucket.WorkRequestDetails;
import com.example.demo.varyingBucket.WRDetailsForVaryingBucket;
import com.example.demo.varyingBucket.WRDetailsForVaryingBucketRepository;

@RestController
public class Controller {

	@Autowired
	private WokrRequestDetailsRepository workRequestDetailsReposiotry;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private WRDetailsForVaryingBucketRepository wrVaryingBucketRepository;
	
	@PostMapping("/rollingbukcet")
	public void rollingBucketServiceCall(@RequestBody User user) {
		

		wrService(user.getUserId(),user.getWorkRequestType());
	
	}
	
	@PostMapping("/varyingbucket")
	public void varyingBucketServiceCall(@RequestBody User user) {
		

		wrVaryingBucketService(user.getUserId(),user.getWorkRequestType());
	
	}
	
	@PostMapping("/fixedbucket")
	public void fixedBucketServiceCall(@RequestBody User user) {
			

		
		}
	
	
	
	public void wrVaryingBucketService(int userId,String workRequestType) {
		
		UserDetails userDetails=new UserDetails();
		WRDetailsForVaryingBucket wrDetailsForVaryingBucket=new WRDetailsForVaryingBucket();

		userDetails=userDetailsRepository.findByUserIdAndWorkRequestType(userId,workRequestType);
		
		wrDetailsForVaryingBucket=wrVaryingBucketRepository.findTopByOrderByIdDesc();
		
		double qrPercent;
		double bucketSize;

		if(wrDetailsForVaryingBucket==null) {
			bucketSize=userDetails.getBucketSize();
			qrPercent=userDetails.getQrPercent();

			WRDetailsForVaryingBucket wd=new WRDetailsForVaryingBucket(userId, workRequestType, 0, false, userDetails.getQrPercent(),bucketSize);
			wrVaryingBucketRepository.save(wd);
			wrDetailsForVaryingBucket=wrVaryingBucketRepository.findByuserId(userId);
		}else {
			qrPercent=wrDetailsForVaryingBucket.getQrPercent();
			bucketSize=wrDetailsForVaryingBucket.getBucketSize();
		}
		
		double wrsToBeSentToQr = (int) Math.ceil((qrPercent * bucketSize) / 100);

		System.out.println("BucketSize: "+bucketSize+"QRPercent: "+qrPercent);
		
		boolean qrOutput=false;
		int workRequestCount=wrDetailsForVaryingBucket.getWorkRequest();
		
		qrOutput=isQrNeedForVaryingBucket(bucketSize, qrPercent,wrsToBeSentToQr);
		
		if(qrOutput) {
			
			bucketSize--;
			wrsToBeSentToQr--;
			qrPercent = Math.round((wrsToBeSentToQr* 100)/ bucketSize);
			WRDetailsForVaryingBucket w=new WRDetailsForVaryingBucket(userId, workRequestType, workRequestCount, true, qrPercent, bucketSize);
			wrVaryingBucketRepository.save(w);
			
		}else {
			bucketSize--;
			qrPercent = Math.round((wrsToBeSentToQr* 100)/ bucketSize);
			WRDetailsForVaryingBucket w=new WRDetailsForVaryingBucket(userId, workRequestType, workRequestCount, false, qrPercent, bucketSize);
			wrVaryingBucketRepository.save(w);

			
		}
	
	}
	
	public boolean isQrNeedForVaryingBucket(double bucketSize,double qrPercent,double wrsToBeSentToQr) {
		
		
//		boolean result = false;
		int randomNumber = (int) (Math.random() * 100);
		
		if (wrsToBeSentToQr != 0) {
			if (randomNumber <= qrPercent) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
		
	}
	
	public void wrService(int userId,String workRequestType) {
		
		UserDetails userDetails=new UserDetails();
		WorkRequestDetails workRequestDetails1=new WorkRequestDetails();
		System.out.println(userId+" "+workRequestType);

		
		
		userDetails=userDetailsRepository.findByUserIdAndWorkRequestType(userId,workRequestType);
		
		workRequestDetails1=workRequestDetailsReposiotry.findTopByOrderByIdDesc();

		if(workRequestDetails1==null) {

			WorkRequestDetails wd=new WorkRequestDetails(userId,workRequestType,0,false);
			workRequestDetailsReposiotry.save(wd);
			workRequestDetails1=workRequestDetailsReposiotry.findByuserId(0);

		}


		int qrPercent=userDetails.getQrPercent();
		int bucketSize=userDetails.getBucketSize();
		boolean qrOutput=false;
		int workRequestCount=workRequestDetails1.getWorkRequest();
		workRequestCount++;

		System.out.println("BucketSize:"+bucketSize+"QRPERCENT:"+qrPercent+"WorkRequest"+workRequestCount);

		qrOutput=isQrNeed(bucketSize, qrPercent, workRequestCount);

		if(qrOutput) {
			
			WorkRequestDetails wd=new WorkRequestDetails(userId,workRequestType,workRequestCount,true);

			workRequestDetailsReposiotry.save(wd);
		}else {
			
			WorkRequestDetails wd=new WorkRequestDetails(userId,workRequestType,workRequestCount,false);
			workRequestDetailsReposiotry.save(wd);
		}
		
	}

	public boolean isQrNeed(int bucketSize,int qrPercent,int workRequestCount) {
	
	boolean Result;
	int minValue=Math.min(bucketSize, workRequestCount);
	int wrsToBeSentToQr=(qrPercent*minValue)/100;
	int numberOfWrsSentCurrentBucket=0;
	
	int difference=bucketSize-workRequestCount;
	
	if(workRequestCount>=bucketSize) {
		
		difference=workRequestCount-bucketSize;
	
		List<WorkRequestDetails> last10=workRequestDetailsReposiotry.findTop10ByUserIdOrderByWorkRequestType(workRequestCount);
	
		for(WorkRequestDetails temp:last10) {
			System.out.println(temp.getWorkRequest());
			if(temp.isQrSent()==true) {
				numberOfWrsSentCurrentBucket++;
			}
		}
		
	}
	
	if(numberOfWrsSentCurrentBucket<=wrsToBeSentToQr) {
		
		if((Result=ProbabilityGenerator())==true) {		  
			   return true;
		}else {
			   return false;
		}	
	}else {
		return false;			   //Update "sent/true" in DB for WorkRequestCount
	}

}
	public 	boolean ProbabilityGenerator() {
		
		return Math.random() < 0.5;
	
	}

}
