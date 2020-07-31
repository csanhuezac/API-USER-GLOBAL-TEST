package com.test.globalLogic.api_rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;

import com.test.globalLogic.api_rest.model.Phone;
import com.test.globalLogic.api_rest.model.ResponseUserModel;
import com.test.globalLogic.api_rest.model.User;
import com.test.globalLogic.api_rest.model.UserResquestModel;
import com.test.globalLogic.api_rest.service.UserService;

@SpringBootTest
public class UserControllerTest {

	@Mock
	private UserService userService;
	
	@Mock
	private User user;

	@Test
	public void testShouldValidatorExceptionWhenMailError() throws Exception {
		UserController controller = new UserController(userService);
		UserResquestModel userRequest = new UserResquestModel();
		userRequest.setEmail("aaa@mail");
		try {
			controller.createUser(userRequest);
		} catch (Exception e) {
			assert (e instanceof Exception);
			assertEquals("Error al enviar Email debe ingresar un mail valido : aaaa@aaa.cl",
					((Exception) e).getMessage());
		}

	}

	@Test
	public void testShouldValidatorExceptionWhenPasswordError() throws Exception {
		UserController controller = new UserController(userService);
		UserResquestModel userRequest = new UserResquestModel();
		userRequest.setEmail("aaa@mail.com");
		userRequest.setPassword("test");
		try {
			controller.createUser(userRequest);
		} catch (Exception e) {
			assert (e instanceof Exception);
			assertEquals("Error al enviar Password debe ingresar al menos : 1 Mayuscula, 1 Minúsculas, y 2 Números)",
					((Exception) e).getMessage());
		}

	}

	@Test
	public void testShouldOkWhenRegisterUser() throws Exception {
		UserController controller = new UserController(userService);
		List<Phone> phones = new ArrayList<>();
		phones.add(this.phoneBuilder());
		UserResquestModel userRequest = new UserResquestModel();
		userRequest.setPhones(phones);
		userRequest.setEmail("aaa@mail.com");
		userRequest.setPassword("Test12");
		User user = userBuilder();
		when(userService.save(any(User.class))).thenReturn(user);


		ResponseUserModel response = controller.createUser(userRequest);
		
		assertNotNull(response);
		assertEquals(response.getId(), user.getId().toString());
	}

	private User userBuilder() {
		User user = new User();
		List<Phone> phones = new ArrayList<>();
		phones.add(this.phoneBuilder());
		
		user.setEmail("aaa@mail.com");
		user.setName("global");
		user.setPassword("Test123");
		user.setCreated(new Date());
		user.setPhones(phones);
		user.setId(java.util.UUID.randomUUID());
		user.setLast_login(new Date());
		user.setModified(new Date());
		
		return user;

	}

	private Phone phoneBuilder() {
		Phone phone = new Phone();

		phone.setCitycode("001");
		phone.setCountrycode("01");
		phone.setId(1L);
		phone.setNumber(40243123);
		
		return phone;
	}

}
