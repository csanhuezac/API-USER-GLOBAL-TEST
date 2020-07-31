package com.test.globalLogic.api_rest.controller;

import java.time.Instant;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.test.globalLogic.api_rest.model.ResponseUserModel;
import com.test.globalLogic.api_rest.model.User;
import com.test.globalLogic.api_rest.model.UserResquestModel;
import com.test.globalLogic.api_rest.service.UserService;
import com.test.globalLogic.api_rest.utils.UtilsValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Value("${jwt.secret}")
	private String secret;
	
	 private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class);

	private final UtilsValidator validator = new UtilsValidator();
	
	public UserController(UserService userService) {
		if (this.userService == null) {
			this.userService = userService;
		}
	}

	public ResponseUserModel createUser(UserResquestModel userRequest) throws Exception {
		
		try {
			validator.validate(userRequest);
			User user = mapUserRequest(userRequest);
			user = userService.save(user);
			LOGGER.info(" :: Save user name: "+user.getName()+" :: email: "+user.getEmail());
			
			return mapUserResponse(user);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	
	public String generateToken(User user) {
		
		LOGGER.info(" :: Generate token JWT ");
		final Instant now = Instant.now();
		String token = "";
		if (secret!= null) {
			token = Jwts.builder().setSubject(user.getEmail()).setIssuedAt(Date.from(now))
					.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret)).compact();
		}
		return token;
	}

	public User mapUserRequest(UserResquestModel userRequest) throws Exception {
		
		LOGGER.info(" :: Mapper userRequest to user : ");
		try {
			User user = new User();
			user.setName(userRequest.getName());
			user.setPassword(userRequest.getPassword());
			user.setEmail(userRequest.getEmail());
			user.setIsactive(true);
			user.setModified(new Date());
			user.setLast_login(new Date());
			user.setCreated(new Date());
			user.setPhones(userRequest.getPhones());
			user.setToken(generateToken(user));

			return user;
		} catch (Exception e) {
			LOGGER.error(" :: Error Mapper user : "+e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}
	
	private ResponseUserModel mapUserResponse(User user) throws Exception {
		LOGGER.info(" :: Mapper mapUserResponse  ");
		try {
			ResponseUserModel userModel = new ResponseUserModel();
			userModel.setCreated(validator.formatDate(user.getCreated()));
			userModel.setId(user.getId().toString());
			userModel.setIsactive(user.getIsactive());
			userModel.setLast_login(validator.formatDate(user.getLast_login()));
			userModel.setModified(validator.formatDate(user.getModified()));
			userModel.setToken(user.getToken());
			
			return userModel;
		} catch (Exception e) {
			LOGGER.error(" :: Error mapUserResponse : "+e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}

}
