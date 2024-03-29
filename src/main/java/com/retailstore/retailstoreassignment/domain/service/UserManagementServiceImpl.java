package com.retailstore.retailstoreassignment.domain.service;

import com.retailstore.retailstoreassignment.config.AppLogger;
import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.exception.DuplicateEmailFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;
import com.retailstore.retailstoreassignment.domain.ports.in.UserManagementService;
import com.retailstore.retailstoreassignment.domain.ports.out.repo.UserRepositoryPort;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class UserManagementServiceImpl implements UserManagementService {

	private Logger log = AppLogger.getLogger(UserManagementServiceImpl.class);

	private UserRepositoryPort userRepositoryPort;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserManagementServiceImpl(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {
		this.userRepositoryPort = userRepositoryPort;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User registerUser(User user) throws DuplicateEmailFoundException {
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepositoryPort.save(user);
	}

	@Override
	public User getUser(String userId) throws UserNotFoundException {
		return userRepositoryPort.findById(userId);
	}

	@Override
	public User getUserByEmail(String email) throws UserNotFoundException {
		return userRepositoryPort.findByEmail(email);
	}

	@Override
	public List<User> getUsers() {
		return userRepositoryPort.findAll();
	}
}
