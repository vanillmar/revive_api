package com.example.revive_app.data.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressResponseDTO {
    private final Long id;
    private final String street;
    private final String city;
    private final String state;
    private final String zipCode;
    private final UUID employeeId; // Assuming this is used to link to an Employee entity
}
