package com.example.cloud_func.controller;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Configuration
public class CloudFunction {

    public static final Logger logger = LoggerFactory.getLogger(CloudFunction.class);

    @Bean
    public Function<StudentData, String> processData() {
        return data -> {
            return data.name() + " " + data.age() + " " + data.rollNo();
        };
    }

    @Bean
    public Function<String, ResponseEntity<?>> print() {
        return str -> {
            System.out.println(str);
            return ResponseEntity.status(HttpStatus.OK).body("Data Printed");
        };
    }
}
