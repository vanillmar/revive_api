/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.response;
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
public class PersonResponseDTO extends BaseAuditableEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String maritalStatus;
    private String nationalId;
    private String bio;
    private Boolean active;
}
