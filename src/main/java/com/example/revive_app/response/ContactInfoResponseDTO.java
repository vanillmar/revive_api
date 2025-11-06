/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ...existing code...
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoResponseDTO {
    private long id;
    private String phoneNumber;
    private String email;
    private String alternateEmail;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private long personId;
    private boolean isPrimary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
}