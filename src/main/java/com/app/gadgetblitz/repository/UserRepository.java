package com.app.gadgetblitz.repository;

import com.app.gadgetblitz.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
