package com.example.BlogProject.exception;

public class UserNotFoundException extends RuntimeException {  // Extend RuntimeException
    public UserNotFoundException(String message) {
        super(message);  // Call the superclass constructor with the error message
    }
}
