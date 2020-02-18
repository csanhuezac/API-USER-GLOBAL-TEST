package com.test.globalLogic.api_rest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.globalLogic.api_rest.model.ResponseErrorModel;
import com.test.globalLogic.api_rest.model.ResponseUserModel;
import com.test.globalLogic.api_rest.model.User;
import com.test.globalLogic.api_rest.model.UserResquestModel;
import com.test.globalLogic.api_rest.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserAPIController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserController userController;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAPIController.class);

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable UUID id) {

		Optional<User> stock = userService.findById(id);
		if (!stock.isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(stock.get());
	}

	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody UserResquestModel userRequest) throws Exception {
		LOGGER.info(" :: Call service create User");
		try {
			ResponseUserModel newUser = userController.createUser(userRequest);
			LOGGER.info(" :: End service create User");
			return Ok(newUser);
		} catch (Exception e) {
			return Error(e.getMessage());
		}

	}

	private ResponseEntity<Object> Error(String message) throws IOException {
		ResponseErrorModel errors = new ResponseErrorModel();
		errors.setMessage(message);
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Object> Ok(ResponseUserModel userModel) throws IOException {

		return new ResponseEntity<>(userModel, HttpStatus.OK);
	}

}
