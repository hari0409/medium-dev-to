package com.example.data_validation.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalCustomException extends RuntimeException {
    public HttpStatus httpStatus;

    public GlobalCustomException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
