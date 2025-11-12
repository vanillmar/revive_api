/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.revive_app.model.Person;
import com.example.revive_app.request.PersonRequestDTO;
import com.example.revive_app.response.PersonResponseDTO;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contactInfos", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Person toEntity(PersonRequestDTO dto);
    
    @Mapping(target = "deleted", ignore = true)
    PersonResponseDTO toResponse(Person entity);

    List<Person> toListEntity(List<PersonRequestDTO> dtos);
    List<PersonResponseDTO> toListResponse(List<Person> entities);
}
