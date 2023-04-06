package com.app.gadgetblitz.controllers;

import com.app.gadgetblitz.models.phone.Phone;
import com.app.gadgetblitz.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class PhoneController {

    PhoneRepository repository;

    @GetMapping("/phones")
    public List<Phone> getAllPhones() {
        return repository.findAll();
    }

    @GetMapping("/phone/{id}")
    public Optional<Phone> getPhoneById(@PathVariable String id) {
        return repository.findById(id);
    }
}
