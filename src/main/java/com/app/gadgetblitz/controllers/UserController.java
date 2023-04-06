package com.app.gadgetblitz.controllers;

import com.app.gadgetblitz.models.user.User;
import com.app.gadgetblitz.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    UserRepository repository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
