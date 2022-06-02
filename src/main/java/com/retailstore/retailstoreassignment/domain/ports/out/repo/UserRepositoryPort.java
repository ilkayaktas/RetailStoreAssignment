package com.retailstore.retailstoreassignment.domain.ports.out.repo;


import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;

public interface UserRepositoryPort {
	User save(User user);

	User
	findById(String userId) throws UserNotFoundException;

	User findByEmail(String email);
}
