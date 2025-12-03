/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.dto;

import com.example.revive_app.model.BaseAuditableEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthRegisterRequestDTO extends BaseAuditableEntity {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private int roleId;
    private boolean isActive;
    private boolean notifications; 
    private boolean enabled;
    private Long personId;
}
