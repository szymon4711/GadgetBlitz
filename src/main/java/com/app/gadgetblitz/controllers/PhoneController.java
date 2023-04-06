package com.app.gadgetblitz.controllers;

import com.app.gadgetblitz.models.Phone;
import com.app.gadgetblitz.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class PhoneController {

    PhoneRepository repository;

    @GetMapping("/phones")
    public List<Phone> getAllPhones() {
        return repository.findAll();
    }
}
