package com.example.revive_app.data.dto;

import com.example.revive_app.model.Role;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {
  private final UUID id;
  private final String username;
  private final String email;
  private final boolean enabled;
  private final Set<Role> roles;
}
