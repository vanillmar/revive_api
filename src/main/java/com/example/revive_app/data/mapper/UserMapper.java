/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.UserRequestDTO;
import com.example.revive_app.data.dto.UserResponseDTO;
import com.example.revive_app.model.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @IgnoreAuditMapping
    User toEntity(UserRequestDTO request);
    List<User> toEntityList(List<UserRequestDTO> requests);

    UserResponseDTO toResponse(User user);
    List<UserResponseDTO> toResponseList(List<User> users);
}
