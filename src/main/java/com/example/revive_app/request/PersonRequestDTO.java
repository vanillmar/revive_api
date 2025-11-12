/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.request;
import java.time.LocalDate;

import com.example.revive_app.model.BaseAuditableEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequestDTO extends BaseAuditableEntity {
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String maritalStatus;
    private String nationalId;
    private String bio;
    private Boolean active;
}
