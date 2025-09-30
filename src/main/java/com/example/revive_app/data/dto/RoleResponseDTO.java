package com.example.revive_app.data.dto;

import com.example.revive_app.model.Role;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class RoleResponseDTO {
    private Set<Role> roles;
}
