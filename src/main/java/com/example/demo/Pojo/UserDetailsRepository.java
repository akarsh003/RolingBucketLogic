package com.example.demo.Pojo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {

//	UserDetails findOneByworkRequestCount(int i);

//	Page<UserDetails> findAllByOrderByDateAsc();

	UserDetails findByuserId(int userid);
	
	UserDetails findByUserIdAndWorkRequestType(int userid, String workrequesttype);

}
