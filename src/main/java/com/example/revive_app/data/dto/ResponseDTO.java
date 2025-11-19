/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO<T> {
    private Instant timestamp;
    private int status;
    private String message;
    private Boolean success;
    private T data;

    private int total; // total items count
    private int page; // current page number
    private int pageSize; // size per page

    public ResponseDTO() {
        this.timestamp = Instant.now();
        this.success = false;
        this.total = 0;
        this.page = 0;
        this.pageSize = 0;
    }

    public ResponseDTO(Boolean success, String message, T data) {
        this();
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
