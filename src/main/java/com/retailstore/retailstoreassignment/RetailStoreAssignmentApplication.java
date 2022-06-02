package com.retailstore.retailstoreassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class RetailStoreAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailStoreAssignmentApplication.class, args);
	}

}
