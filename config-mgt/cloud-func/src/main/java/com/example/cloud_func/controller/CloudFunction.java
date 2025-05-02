package com.example.cloud_func.controller;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudFunction {

    public static final Logger logger = LoggerFactory.getLogger(CloudFunction.class);

    @Bean
    public Function<String, Integer> getMyStringLength() {
        return str -> {
            logger.info("Processing String: " + str);
            return str.length();
        };
    }

}
