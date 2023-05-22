package com.app.gadgetblitz.model.token;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, String> {

    List<Token> findAllByUser_IdAndExpiredIsFalseOrRevokedIsFalse(String userId);

    Optional<Token> findByToken(String token);
}
