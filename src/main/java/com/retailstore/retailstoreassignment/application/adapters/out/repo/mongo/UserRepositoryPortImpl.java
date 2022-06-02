package com.retailstore.retailstoreassignment.application.adapters.out.repo.mongo;

import com.retailstore.retailstoreassignment.domain.model.entity.User;
import com.retailstore.retailstoreassignment.domain.model.exception.UserNotFoundException;
import com.retailstore.retailstoreassignment.domain.ports.out.repo.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by ilkayaktas on 22.03.2022 at 13:29.
 */
@Service
public class UserRepositoryPortImpl implements UserRepositoryPort {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public User save(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public User findById(String userId) throws UserNotFoundException {
        User user = mongoTemplate.findById(userId, User.class);

        if (user == null)throw new UserNotFoundException("User not found");

        return user;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
