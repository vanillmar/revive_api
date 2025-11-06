/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.model.Person;
import com.example.revive_app.request.PersonRequestDTO;
import com.example.revive_app.response.PersonResponseDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contactInfos", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "primaryAddress", ignore = true)
    @Mapping(target = "user", ignore = true)
    Person toEntity(PersonRequestDTO dto);
    List<Person> toListEntity(List<PersonRequestDTO> dtos);

    PersonResponseDTO toResponse(Person entity);
    List<PersonResponseDTO> toListResponse(List<Person> entities);
}
