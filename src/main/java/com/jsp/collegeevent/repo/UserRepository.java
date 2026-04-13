package com.jsp.collegeevent.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.collegeevent.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	
	 User findByEmail(String email);

}
