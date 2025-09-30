package com.example.revive_app.data.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO <T> {
    private Instant timestamp;
    private int status;
    private String message;
    private Boolean success;
    private T data;

    public ResponseDTO() {
        this.timestamp = Instant.now();
        this.success = false;
    }
}