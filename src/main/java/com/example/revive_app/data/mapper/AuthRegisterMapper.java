package com.example.revive_app.data.mapper;

import java.util.HashSet;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.revive_app.data.dto.AuthRegisterRequestDTO;
import com.example.revive_app.data.dto.UserRequestDTO;
import com.example.revive_app.model.Role;

@Mapper(componentModel = "Spring")
public interface AuthRegisterMapper {
  @Mapping(target = "id", ignore = true) // ID is not present in source; can be set to null or generated elsewhere
  @Mapping(source = "username", target = "username")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "password", target = "password")
  @Mapping(target = "enabled", ignore= true)
  @Mapping(target = "roles", expression = "java(mapRoles(source.getRoleId()))")
  UserRequestDTO toUserRequestDTO(AuthRegisterRequestDTO source);

  // Default method to handle roleId to Set<Role> mapping
  // Assumes Role has a no-arg constructor and a setter for ID (adjust if Role's ID type differs; here assuming Integer/Long)
  default Set<Role> mapRoles(int roleId) {
      Role role = new Role();
      role.setId((long) roleId); // Cast to Long if Role ID is Long; adjust based on actual Role model
      return new HashSet<>(Set.of(role));
  }
}