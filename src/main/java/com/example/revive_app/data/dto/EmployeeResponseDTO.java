/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.dto;

import com.example.revive_app.model.Role;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeResponseDTO {
    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private boolean enabled;
    private Set<Role> roles;
    private Long department;
}
