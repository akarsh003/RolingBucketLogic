package com.example.demo.rollingBucket;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WokrRequestDetailsRepository extends JpaRepository<WorkRequestDetails,Integer> {

	WorkRequestDetails findByuserId(int userId);

	WorkRequestDetails findTopByOrderByIdDesc();
	
	List<WorkRequestDetails>  findTop10ByUserIdOrderByWorkRequestType(int userid);


	
}

