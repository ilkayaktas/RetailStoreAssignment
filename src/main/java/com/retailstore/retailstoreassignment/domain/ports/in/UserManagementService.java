package com.retailstore.retailstoreassignment.domain.ports.in;

import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;

public interface UserManagementService {
	User registerUser(User userVO);

	User getUser(String userId) throws UserNotFoundException;
}
