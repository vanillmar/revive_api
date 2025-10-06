/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressResponseDTO {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private UUID employeeId; // Assuming this is used to link to an Employee entity
}
