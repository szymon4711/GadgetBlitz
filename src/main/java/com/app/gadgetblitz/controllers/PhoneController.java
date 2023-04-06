package com.app.gadgetblitz.controllers;

import com.app.gadgetblitz.models.phone.Phone;
import com.app.gadgetblitz.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class PhoneController {

    PhoneRepository repository;
    MongoTemplate mongoTemplate;

    @GetMapping("/phones")
    public List<Phone> getAllPhones() {
        return repository.findAll();
    }

    @GetMapping("/phone/{id}")
    public Optional<Phone> getPhoneById(@PathVariable String id) {
        return repository.findById(id);
    }


    @GetMapping("/test")
    public List<Phone> getPhone(
            @RequestParam(required = false) String marka,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer price) {

        Criteria criteria = new Criteria();
        if (marka != null)
            criteria = criteria.and("brand").is(marka);
        if (model != null)
            criteria = criteria.and("model").is(model);
        if(price != null)
            criteria = criteria.and("price").lt(price);

        Query query = new Query(criteria);

        return mongoTemplate.find(query, Phone.class);


    }
}
