package com.example.revive_app.data.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {
  private Long id;
  private String street;
  private String city;
  private String state;
  private String zipCode;
  private UUID employeeId; // Assuming this is used to link to an Employee entity
}
