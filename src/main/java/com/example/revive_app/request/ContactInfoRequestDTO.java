/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoRequestDTO {
    private String phoneNumber;
    private String email;
    private String alternateEmail;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private long personId;
}
