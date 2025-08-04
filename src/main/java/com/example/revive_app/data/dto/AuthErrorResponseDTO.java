package com.example.revive_app.data.dto;

import java.time.Instant;

public class AuthErrorResponseDTO extends AuthResponse implements ErrorResponseDTO {
    private final Instant timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path; 

    public AuthErrorResponseDTO(Instant timestamp, int status, String error, String message, String path) {
        super("");
        this.timestamp = timestamp;
        this.status = status;
        this.error = error; 
        this.message = message;
        this.path = path;
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getPath() {
        return path;
    }
}
