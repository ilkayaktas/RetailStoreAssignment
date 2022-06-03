package com.retailstore.retailstoreassignment.domain.ports.out.repo;


import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.exception.DuplicateEmailFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;

import java.util.List;

public interface UserRepositoryPort {
	User save(User user) throws DuplicateEmailFoundException;

	User
	findById(String userId) throws UserNotFoundException;

	User findByEmail(String email) throws UserNotFoundException;

	List<User> findAll();
}
