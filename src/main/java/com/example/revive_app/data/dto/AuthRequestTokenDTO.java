/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.dto;

public class AuthRequestTokenDTO {
    private final String token;

    public AuthRequestTokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
