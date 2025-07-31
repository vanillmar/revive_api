package com.example.revive_app.data.dto;

import java.util.Set;
import java.util.UUID;

import com.example.revive_app.model.Role;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class EmployeeResponseDTO {
    private final UUID id;
    private final String firstname;
    private final String lastname;
    private final String username;
    private final String email;
    private final boolean enabled;
    private final Set<Role> roles;
}
