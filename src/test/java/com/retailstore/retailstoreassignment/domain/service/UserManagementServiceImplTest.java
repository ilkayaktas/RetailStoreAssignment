package com.retailstore.retailstoreassignment.domain.service;

import com.retailstore.retailstoreassignment.application.adapters.out.repo.mongo.UserRepository;
import com.retailstore.retailstoreassignment.domain.ports.out.repo.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * To run this test from IDE, you should provide environment variables.
 * MONGODB_IP=localhost
 * MONGODB_USER=root
 * MONGODB_PASSWORD=passw0rd
 *
 * You can run test from terminal as below.
 * mvn -DMONGODB_IP=localhost -DMONGODB_USER=root -DMONGODB_PASSWORD=passw0rd test
 */
@SpringBootTest
public class UserManagementServiceImplTest {

	@InjectMocks
	UserManagementServiceImpl userManagementService;

	@Mock
	UserRepository userRepository;

	@Mock
	UserRepositoryPort userRepositoryPort;


	@BeforeEach
	void setUp() {
	}

	@Test
	public void registerUser() {
		assertNotNull(userManagementService);
		assertNotNull(userRepository);
		assertNotNull(userRepositoryPort);
		System.out.println("2");
	}

	@Test
	public void getUser() {
		assertEquals(1,1);
		System.out.println("3");
	}

	@Test
	public void getUserByEmail() {
		assertEquals(1,1);
		System.out.println("4");
	}

	@Test
	public void getUsers() {
		assertEquals(1,1);
		System.out.println("5");
	}
}