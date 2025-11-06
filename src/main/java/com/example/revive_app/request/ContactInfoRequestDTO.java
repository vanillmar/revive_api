/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.request;

import com.example.revive_app.model.BaseAuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoRequestDTO extends BaseAuditableEntity {
    private String phoneNumber;
    private String email;
    private String alternateEmail;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private Long personId;
    private boolean isPrimary;
    private String updatedBy;
}
