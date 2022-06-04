package com.retailstore.retailstoreassignment.application.adapters.out.repo.mongo;

import com.retailstore.retailstoreassignment.domain.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by ilkayaktas on 3.06.2022 at 00:02.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findUserByEmail(String email);
}
