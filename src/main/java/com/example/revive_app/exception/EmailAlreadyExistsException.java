package com.example.revive_app.exception;

public class EmailAlreadyExistsException extends RuntimeException  {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
