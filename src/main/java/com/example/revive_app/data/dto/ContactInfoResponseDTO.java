/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.dto;

import com.example.revive_app.model.BaseAuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ...existing code...
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoResponseDTO extends BaseAuditableEntity {
    private long id;
    private String phoneNumber;
    private String email;
    private String alternateEmail;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private long personId;
    private boolean primary;

}