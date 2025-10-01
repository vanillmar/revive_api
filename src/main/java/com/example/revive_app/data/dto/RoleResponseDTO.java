package com.example.revive_app.data.dto;

import com.example.revive_app.model.Role;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleResponseDTO {
  private Set<Role> roles;
}
