package com.bodify.bodify.repository;

import com.bodify.bodify.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
