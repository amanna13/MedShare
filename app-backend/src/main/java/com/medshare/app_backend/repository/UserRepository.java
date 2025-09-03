package com.medshare.app_backend.repository;

import com.medshare.app_backend.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    boolean existsUserByUserName(String userName);
    Optional<User> findUserByUserName(String userName);
}
