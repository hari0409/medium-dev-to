package com.example.data_validation.validation.validator;

import com.example.data_validation.validation.annotations.UsernameValid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidValidator implements ConstraintValidator<UsernameValid, String> {

    private String exemption;

    public void initialize(UsernameValid constrainUsernameValid) {
        this.exemption = constrainUsernameValid.exemption();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (exemption.equals("true")) {
            return true;
        }
        return value.contains("data-validation.com");
    }
}