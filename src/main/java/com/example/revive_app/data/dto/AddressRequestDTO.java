/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.dto;

import com.example.revive_app.model.BaseAuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO extends BaseAuditableEntity {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private Long personId;
    private boolean primary;
    private String updatedBy;
}
