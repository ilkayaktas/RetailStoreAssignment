package com.retailstore.retailstoreassignment.domain.ports.in;

import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.exception.DuplicateEmailFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;

import java.util.List;

public interface UserManagementService {
	User registerUser(User userVO) throws DuplicateEmailFoundException;

	User getUser(String userId) throws UserNotFoundException;

	User getUserByEmail(String email) throws UserNotFoundException;

	List<User> getUsers();
}
