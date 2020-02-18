package com.test.globalLogic.api_rest.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.test.globalLogic.api_rest.model.User;

public abstract class UserRepositoryImpl implements UserRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	@Autowired
    EntityManager entityManager;
	
	@Override
	public User findByEmail(String email) {
		LOGGER.info(" :: Find register Email "+email);
		Query query = entityManager.createQuery("select * from API_USER_USER where email = "+email);
		User user = (User) query.getSingleResult();
		
		return user;
	}
	
	

	

}
