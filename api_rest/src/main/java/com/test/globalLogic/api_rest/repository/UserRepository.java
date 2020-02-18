package com.test.globalLogic.api_rest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.globalLogic.api_rest.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	public User findByEmail(String email);

}
