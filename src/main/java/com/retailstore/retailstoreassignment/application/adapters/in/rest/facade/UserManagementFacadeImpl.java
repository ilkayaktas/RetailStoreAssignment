package com.retailstore.retailstoreassignment.application.adapters.in.rest.facade;

import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.mapper.UserMapper;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.request.UserRequestDto;
import com.retailstore.retailstoreassignment.application.adapters.in.rest.dto.response.UserResponseDto;
import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.exception.DuplicateEmailFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;
import com.retailstore.retailstoreassignment.domain.ports.in.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementFacadeImpl implements UserManagementFacade {
	private UserManagementService userManagementService;

	@Autowired
	public UserManagementFacadeImpl(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}

	@Override
	public UserResponseDto registerUser(UserRequestDto userRequestDto) throws DuplicateEmailFoundException {
		User user = UserMapper.INSTANCE.toUser(userRequestDto);

		User resVal = userManagementService.registerUser(user);

		return UserMapper.INSTANCE.toUserRestDto(resVal);
	}

	@Override
	public UserResponseDto getUser(String userId) throws UserNotFoundException {
		User resVal = userManagementService.getUser(userId);

		return UserMapper.INSTANCE.toUserRestDto(resVal);
	}

	@Override
	public User getUserByEmail(String email) throws UserNotFoundException {
		User resVal = userManagementService.getUserByEmail(email);

		return resVal;
	}

	@Override
	public List<UserResponseDto> getUsers() {
		List<User> users = userManagementService.getUsers();
		return users.stream().map(UserMapper.INSTANCE::toUserRestDto).toList();
	}
}
