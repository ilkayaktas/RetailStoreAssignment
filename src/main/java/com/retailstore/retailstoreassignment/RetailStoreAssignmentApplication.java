package com.retailstore.retailstoreassignment;

import com.retailstore.retailstoreassignment.config.AppLogger;
import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.enums.UserType;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;
import com.retailstore.retailstoreassignment.domain.ports.in.UserManagementService;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class RetailStoreAssignmentApplication implements CommandLineRunner {

	private Logger logger = AppLogger.getLogger(RetailStoreAssignmentApplication.class);

	private final UserManagementService userManagementService;

	public RetailStoreAssignmentApplication(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RetailStoreAssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try{
			userManagementService.getUserByEmail("admin@gmail.com");
			logger.info("Admin is already created.");
		} catch (UserNotFoundException e){
			userManagementService.registerUser(User.builder()
					.email("admin@gmail.com")
					.name("admin")
					.lastName("admin")
					.password("pasw0rd123")
					.userType(UserType.ADMIN)
					.build());

			logger.info("Admin is created.");
		}

	}
}
