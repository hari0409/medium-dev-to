package com.example.data_validation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.data_validation.entties.User;
import com.example.data_validation.repo.UserRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserRepo userRepo;

    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody JsonNode requestBody) {
        User user = objectMapper.convertValue(requestBody.get("user"), User.class);
        System.out.println("Before Saving");
        User savedUser = userRepo.save(user);
        return ResponseEntity.status(200).body(savedUser);
    }

}
