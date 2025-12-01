/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.revive_app.data.dto.UserRequestDTO;
import com.example.revive_app.data.dto.UserResponseDTO;
import com.example.revive_app.model.Person;
import com.example.revive_app.model.User;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    User toEntity(UserRequestDTO request);

    UserResponseDTO toResponse(User user);

    List<User> toEntityList(List<UserRequestDTO> requests);

    List<UserResponseDTO> toResponseList(List<User> users);

    @Named("idToPerson")
    default Person idToPerson(Long id) {
        if (id == null)
            return null;
        Person p = new Person();
        p.setId(id);
        return p;
    }
}
