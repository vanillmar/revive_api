/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.dto;

import com.example.revive_app.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private UUID id;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Email must be valid")
    private String email;

    private String password;

    private boolean enabled;

    private Set<Role> roles = new HashSet<>();
}
