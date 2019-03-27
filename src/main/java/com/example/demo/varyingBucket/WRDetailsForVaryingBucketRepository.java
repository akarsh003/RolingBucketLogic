package com.example.demo.varyingBucket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WRDetailsForVaryingBucketRepository extends JpaRepository<WRDetailsForVaryingBucket,Integer> {

	WRDetailsForVaryingBucket findByuserId(int userId);

	WRDetailsForVaryingBucket findTopByOrderByIdDesc();
	
	List<WRDetailsForVaryingBucket>  findTop10ByUserIdOrderByWorkRequestType(int userid);


	
}
