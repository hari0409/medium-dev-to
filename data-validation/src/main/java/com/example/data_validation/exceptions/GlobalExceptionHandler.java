package com.example.data_validation.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.data_validation.response.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(exception = GlobalCustomException.class)
    public ResponseEntity<ErrorResponse> handleGlobalCustomException(GlobalCustomException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorResponse(ex.getMessage(), ex.getHttpStatus()));
    }

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleGlobalCustomException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(exception = ConstraintViolationException.class)
    public ResponseEntity<?> handleDataValidationExceptions(ConstraintViolationException ex) {
        Map<String, String> errorMap = parseConstraintViolationExceptionMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    public Map<String, String> parseConstraintViolationExceptionMessage(String exceptionMessage) {
        Map<String, String> errors = new HashMap<>();

        // Find the start of the list of constraint violations
        int startIndex = exceptionMessage.indexOf("List of constraint violations:[");
        if (startIndex == -1)
            return errors;

        // Extract the violations part
        startIndex += "List of constraint violations:[".length();
        int endIndex = exceptionMessage.lastIndexOf("]");
        String violationsText = exceptionMessage.substring(startIndex, endIndex).trim();

        // Split by the constraint violation pattern
        String[] violations = violationsText.split("\\n\\t");

        for (String violation : violations) {
            if (violation.isEmpty())
                continue;

            // Extract interpolatedMessage
            String messagePattern = "interpolatedMessage='";
            int messageStartIndex = violation.indexOf(messagePattern) + messagePattern.length();
            int messageEndIndex = violation.indexOf("'", messageStartIndex);
            String message = violation.substring(messageStartIndex, messageEndIndex);

            // Extract propertyPath
            String pathPattern = "propertyPath=";
            int pathStartIndex = violation.indexOf(pathPattern) + pathPattern.length();
            int pathEndIndex = violation.indexOf(",", pathStartIndex);
            String path = violation.substring(pathStartIndex, pathEndIndex);

            errors.put(path, message);
        }

        return errors;
    }
}
