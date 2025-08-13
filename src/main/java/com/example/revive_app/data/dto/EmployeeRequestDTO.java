package com.example.revive_app.data.dto;

import com.example.revive_app.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDTO {
  private UUID id;

  @NotBlank(message = "Firstname is required")
  private String firstname;

  @NotBlank(message = "Lastname is required")
  private String lastname;

  @NotBlank(message = "Username is required")
  private String username;

  @NotBlank(message = "Password is required")
  private String password;

  @Email(message = "Email must be valid")
  private String email;

  private boolean enabled;

  @NotBlank(message = "Role is required")
  private Set<Role> roles = new HashSet<>();

  private DepartmentRequestDTO department;
}
