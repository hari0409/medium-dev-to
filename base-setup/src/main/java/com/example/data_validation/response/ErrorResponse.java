package com.example.data_validation.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {
    public String errorMsg;
    HttpStatus httpStatus;
}
