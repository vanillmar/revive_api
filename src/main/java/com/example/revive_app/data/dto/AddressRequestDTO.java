package com.example.revive_app.data.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
  @NotBlank(message = "street is mandatory")
  @Size(min = 2, max = 50, message = "street must be between 2 and 50 characters")
  private String street;
  
  @NotBlank(message = "City is mandatory")
  @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
  private String city;
  
  @NotBlank(message = "State is mandatory")
  @Size(min = 2, max = 50, message = "State must be between 2 and 50 characters")
  private String state;
  
  @NotBlank(message = "Zip Code is mandatory")
  @Pattern(
      regexp = "^[0-9]{5}(?:-[0-9]{4})?$",
      message = "Invalid ZIP code format"
  )  
  private String zipCode;
      
  @NotBlank(message = "Name is mandatory")
  private UUID employeeId; // Assuming this is used to link to an Employee entity
}
