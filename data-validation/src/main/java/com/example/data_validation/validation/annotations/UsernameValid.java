package com.example.data_validation.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.data_validation.validation.validator.UsernameValidValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidValidator.class)
@Documented
public @interface UsernameValid {
    String message() default "Username format not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String exemption() default "false";
}