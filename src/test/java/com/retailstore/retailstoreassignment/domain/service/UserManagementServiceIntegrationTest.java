package com.retailstore.retailstoreassignment.domain.service;

import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.enums.UserType;
import com.retailstore.retailstoreassignment.domain.model.exception.DuplicateEmailFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;
import com.retailstore.retailstoreassignment.domain.ports.out.repo.UserRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

	@InjectMocks
	UserManagementServiceImpl userManagementService;

	@Mock
	UserRepositoryPort userRepositoryPort;

	@Mock
	PasswordEncoder passwordEncoder;

	@Test
	public void registerUser() throws DuplicateEmailFoundException {
		String email = "user1@gmail.com";
		String name = "user1";
		String lastname = "user1";
		String password = "pasw0rd123";
		String passwordEncoded = "pasw0rd123-encoded";

		User user = User.builder()
				.email(email)
				.name(name)
				.lastName(lastname)
				.password(password)
				.userType(UserType.AFFILIATE)
				.build();

		LocalDateTime now = LocalDateTime.now();

		String id = UUID.randomUUID().toString().replace("-","");
		User userRet = User.builder()
				.id(id)
				.createdAt(now)
				.email(email)
				.name(name)
				.lastName(lastname)
				.password(passwordEncoded)
				.build();

		Mockito.when(userRepositoryPort.save(user)).thenReturn(userRet);
		Mockito.when(passwordEncoder.encode(password)).thenReturn(passwordEncoded);
		User res = userManagementService.registerUser(user);

		assertEquals(id, res.getId());
		assertEquals(userRet.getEmail(), res.getEmail());
		assertEquals(userRet.getUserType(), res.getUserType());
		assertEquals(userRet.getName(), res.getName());
		assertEquals(userRet.getCreatedAt(), res.getCreatedAt());
		assertEquals(userRet.getPassword(), res.getPassword());
	}

	@Test
	public void getUser() throws UserNotFoundException {
		String userId = UUID.randomUUID().toString().replace("-","");
		LocalDateTime now = LocalDateTime.now();

		String email = "user1@gmail.com";
		String name = "user1";
		String lastname = "user1";
		String password = "pasw0rd123";
		String passwordEncoded = "pasw0rd123-encoded";

		User userRet = User.builder()
				.id(userId)
				.createdAt(now)
				.email(email)
				.name(name)
				.lastName(lastname)
				.password(passwordEncoded)
				.build();

		Mockito.when(userRepositoryPort.findById(userId)).thenReturn(userRet);

		User res = userManagementService.getUser(userId);

		assertEquals(userId, res.getId());
		assertEquals(userRet.getEmail(), res.getEmail());
		assertEquals(userRet.getUserType(), res.getUserType());
		assertEquals(userRet.getName(), res.getName());
		assertEquals(userRet.getCreatedAt(), res.getCreatedAt());
		assertEquals(userRet.getPassword(), res.getPassword());
	}

	@Test
	public void getUserByEmail() throws UserNotFoundException {
		String userId = UUID.randomUUID().toString().replace("-","");
		LocalDateTime now = LocalDateTime.now();

		String email = "user1@gmail.com";
		String name = "user1";
		String lastname = "user1";
		String password = "pasw0rd123";
		String passwordEncoded = "pasw0rd123-encoded";

		User userRet = User.builder()
				.id(userId)
				.createdAt(now)
				.email(email)
				.name(name)
				.lastName(lastname)
				.password(passwordEncoded)
				.build();

		Mockito.when(userRepositoryPort.findByEmail(email)).thenReturn(userRet);

		User res = userManagementService.getUserByEmail(email);

		assertEquals(userId, res.getId());
		assertEquals(userRet.getEmail(), res.getEmail());
		assertEquals(userRet.getUserType(), res.getUserType());
		assertEquals(userRet.getName(), res.getName());
		assertEquals(userRet.getCreatedAt(), res.getCreatedAt());
		assertEquals(userRet.getPassword(), res.getPassword());
	}

	@Test
	public void getUsers() {
		String userId = UUID.randomUUID().toString().replace("-","");
		LocalDateTime now = LocalDateTime.now();

		String email = "user1@gmail.com";
		String name = "user1";
		String lastname = "user1";
		String password = "pasw0rd123";
		String passwordEncoded = "pasw0rd123-encoded";

		User userRet = User.builder()
				.id(userId)
				.createdAt(now)
				.email(email)
				.name(name)
				.lastName(lastname)
				.password(passwordEncoded)
				.build();

		User userRet2 = User.builder()
				.id(userId)
				.createdAt(now)
				.email(email)
				.name(name)
				.lastName(lastname)
				.password(passwordEncoded)
				.build();

		Mockito.when(userRepositoryPort.findAll()).thenReturn(List.of(userRet, userRet2));

		List<User> users = userManagementService.getUsers();

		assertEquals(userId, users.get(0).getId());
		assertEquals(userRet.getEmail(), users.get(0).getEmail());
		assertEquals(userRet.getUserType(), users.get(0).getUserType());
		assertEquals(userRet.getName(), users.get(0).getName());
		assertEquals(userRet.getCreatedAt(), users.get(0).getCreatedAt());
		assertEquals(userRet.getPassword(), users.get(0).getPassword());

		assertEquals(userId, users.get(1).getId());
		assertEquals(userRet.getEmail(), users.get(1).getEmail());
		assertEquals(userRet.getUserType(), users.get(1).getUserType());
		assertEquals(userRet.getName(), users.get(1).getName());
		assertEquals(userRet.getCreatedAt(), users.get(1).getCreatedAt());
		assertEquals(userRet.getPassword(), users.get(1).getPassword());
	}
}