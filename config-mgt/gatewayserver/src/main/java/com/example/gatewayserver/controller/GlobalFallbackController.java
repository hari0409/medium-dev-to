package com.example.gatewayserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class GlobalFallbackController {
    @GetMapping("/server-fallback")
    public Mono<ResponseEntity<String>> serverFallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Occurred, Try in sometime."));
    }

}
