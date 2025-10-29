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
public class AddressRequestDTO {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private Long personId;
    private boolean isPrimary;
}
