package com.retailstore.retailstoreassignment.application.adapters.out.repo.mongo;

import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.exception.DuplicateEmailFoundException;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;
import com.retailstore.retailstoreassignment.domain.ports.out.repo.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by ilkayaktas on 22.03.2022 at 13:29.
 */
@Service
public class UserRepositoryPortImpl implements UserRepositoryPort {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryPortImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) throws DuplicateEmailFoundException {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        if (userByEmail.isPresent())throw new DuplicateEmailFoundException("This email is already used.");

        return userRepository.save(user);
    }

    @Override
    public User findById(String userId) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()){
            return user.get();
        }

        throw new UserNotFoundException("User not found");

    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        Optional<User> userByEmail = userRepository.findUserByEmail(email);

        if (userByEmail.isPresent()){
            return userByEmail.get();
        }

        throw new UserNotFoundException("User not found");
    }
}
