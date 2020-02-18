package com.test.globalLogic.api_rest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.globalLogic.api_rest.model.User;
import com.test.globalLogic.api_rest.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	 private static final Logger LOGGER=LoggerFactory.getLogger(UserRepository.class);


	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(UUID id) {
		return userRepository.findById(id);
	}

	public User save(User user) throws Exception {
		User usr = userRepository.findByEmail(user.getEmail());
		if (usr !=null ){
			LOGGER.error(" :: Error email ya existe");
			throw new Exception("Error al registrar usuario email ya existe "+user.getEmail());
		}
		return userRepository.save(user);
	}

	public void deleteById(UUID id) {
		userRepository.deleteById(id);
	}

}
