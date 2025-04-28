package com.example.data_validation.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.data_validation.entties.User;
import com.example.data_validation.repo.UserRepo;
import com.example.data_validation.validation.groups.Scenario1;
import com.example.data_validation.validation.groups.Scenario2;
import com.example.data_validation.validation.groups.Sequence;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserRepo userRepo;

    private ObjectMapper objectMapper;

    @Autowired
    public Validator validator;

    // @PostMapping("/create")
    // public ResponseEntity<?> createUser(@RequestBody JsonNode requestBody) {
    // User user = objectMapper.convertValue(requestBody.get("user"), User.class);
    // Map<String, String> errors = new HashMap<String, String>();
    // Set<ConstraintViolation<User>> violations = validator.validate(user,
    // Scenario1.class);
    // for (ConstraintViolation<User> violation : violations) {
    // errors.put(violation.getPropertyPath().toString(), violation.getMessage());
    // }
    // if (errors.size() > 0) {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    // }
    // User savedUser = userRepo.save(user);
    // return ResponseEntity.status(200).body(savedUser);
    // }

    @PostMapping("/create")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepo.save(user));
    }
}
