/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.dto;
import com.example.revive_app.model.BaseAuditableEntity;
import java.time.LocalDate;
import java.util.List;
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
    private List<ContactInfoRequestDTO> contactInfos;
    private List<AddressRequestDTO> addresses;
}
