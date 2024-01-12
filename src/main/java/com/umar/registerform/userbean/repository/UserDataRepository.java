package com.umar.registerform.userbean.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.umar.registerform.userbean.UserData;



public interface UserDataRepository extends JpaRepository<UserData, Long> {
	
	
	public UserData findByEmail(String email);

	

	
	
	
}