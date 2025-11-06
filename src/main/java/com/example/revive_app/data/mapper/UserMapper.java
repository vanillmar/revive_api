/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.UserRequestDTO;
import com.example.revive_app.data.dto.UserResponseDTO;
import com.example.revive_app.model.Person;
import com.example.revive_app.model.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "personId", target = "person", qualifiedByName = "idToPerson")
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    User toEntity(UserRequestDTO request);
    List<User> toEntityList(List<UserRequestDTO> requests);

    UserResponseDTO toResponse(User user);
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
