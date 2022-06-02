package com.retailstore.retailstoreassignment.application.adapters.in.rest.facade;

import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.UserRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.UserResponseDto;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;

public interface UserManagementFacade {

	UserResponseDto registerUser(UserRequestDto userRequestDto);

	UserResponseDto getUser(String userId) throws UserNotFoundException;
}
