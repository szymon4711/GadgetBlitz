package com.app.gadgetblitz.repository;

import com.app.gadgetblitz.models.phone.Phone;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhoneRepository extends MongoRepository<Phone, String> {
}
