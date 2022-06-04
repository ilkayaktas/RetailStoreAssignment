package com.retailstore.retailstoreassignment.domain.service;

import com.retailstore.retailstoreassignment.application.adapters.out.repo.mongo.UserRepository;
import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.exception.DuplicateEmailFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is integration test class. All spring context is invoked and run test. To do this, mongodb should be running.
 * Even embedded mongodb is an option, I didn't prefer to use embedded mongodb.
 * To run this test from IDE, you should provide environment variables.
 * MONGODB_IP=localhost
 * MONGODB_USER=root
 * MONGODB_PASSWORD=passw0rd
 *
 * You can run test from terminal as below.
 * mvn -DMONGODB_IP=localhost -DMONGODB_USER=root -DMONGODB_PASSWORD=passw0rd test
 */
@SpringBootTest
public class UserManagementServiceIntegrationTest {

	@Autowired
	UserManagementServiceImpl userManagementService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void getUsers() throws DuplicateEmailFoundException {

		String email = "user1@gmail.com";
		String name = "user1";
		String lastname = "user1";
		String password = "pasw0rd123";

		User userRet = User.builder()
				.email(email+1)
				.name(name)
				.lastName(lastname)
				.password(password)
				.build();

		User userRet2 = User.builder()
				.email(email+2)
				.name(name)
				.lastName(lastname)
				.password(password)
				.build();


		userManagementService.registerUser(userRet);
		userManagementService.registerUser(userRet2);
		List<User> users = userManagementService.getUsers();

		assertEquals(userRet.getEmail(), users.get(0).getEmail());
		assertEquals(userRet.getUserType(), users.get(0).getUserType());
		assertEquals(userRet.getName(), users.get(0).getName());

		assertEquals(userRet2.getEmail(), users.get(1).getEmail());
		assertEquals(userRet2.getUserType(), users.get(1).getUserType());
		assertEquals(userRet2.getName(), users.get(1).getName());

	}

	@BeforeEach
	public void clearUsers(){
		userRepository.deleteAll();
	}

	@AfterEach
	public void revertUsers(){
		userRepository.deleteAll();
	}

}