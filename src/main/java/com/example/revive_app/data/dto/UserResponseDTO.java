package com.example.revive_app.data.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.revive_app.model.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String username;
    private String email;
    private boolean enabled;
    private Set<Role> roles = new HashSet<>(); 
}
