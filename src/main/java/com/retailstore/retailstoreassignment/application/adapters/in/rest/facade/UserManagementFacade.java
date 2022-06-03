package com.retailstore.retailstoreassignment.application.adapters.in.rest.facade;

import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.UserRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.UserResponseDto;
import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.exception.DuplicateEmailFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;

import java.util.List;

public interface UserManagementFacade {

	UserResponseDto registerUser(UserRequestDto userRequestDto) throws DuplicateEmailFoundException;

	UserResponseDto getUser(String userId) throws UserNotFoundException;

	User getUserByEmail(String email) throws UserNotFoundException;

	List<UserResponseDto> getUsers();
}
