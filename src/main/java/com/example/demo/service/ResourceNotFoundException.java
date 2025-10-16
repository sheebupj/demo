package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // Maps this exception to HTTP 404
public class ResourceNotFoundException extends RuntimeException {

    // Constructor to pass a custom message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
