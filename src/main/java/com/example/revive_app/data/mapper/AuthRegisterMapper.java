/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.AuthRegisterRequestDTO;
import com.example.revive_app.data.dto.UserRequestDTO;
import com.example.revive_app.model.Role;
import java.util.HashSet;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface AuthRegisterMapper {
    @Mapping(target = "id", ignore = true) // ID is not present in source; can be set to null or generated elsewhere
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "roles", expression = "java(mapRoles(source.getRoleId()))")
    UserRequestDTO toUserRequestDTO(AuthRegisterRequestDTO source);

    default Set<Role> mapRoles(int roleId) {
        Role role = new Role();
        role.setId((long) roleId);
        return new HashSet<>(Set.of(role));
    }
}
