/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.dto;

import com.example.revive_app.model.Role;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

@Data
public class UserResponseDTO {
    private UUID id;
    private String username;
    private String email;
    private boolean enabled;
    private Long personId;
    private Set<Role> roles;
    private boolean notifications;
    private String avatar;
}
