/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.PersonRequestDTO;
import com.example.revive_app.data.dto.PersonResponseDTO;
import com.example.revive_app.model.Person;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ContactInfoMapper.class, AddressMapper.class})
public interface PersonMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "addresses", target = "addresses", qualifiedByName = "toAddressListEntity")
    @Mapping(source = "contactInfos", target = "contactInfos", qualifiedByName = "toContactInfoListEntity")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Person toEntity(PersonRequestDTO dto);

    @Mapping(source = "addresses", target = "addresses")
    @Mapping(source = "contactInfos", target = "contactInfos")
    PersonResponseDTO toResponse(Person entity);

    List<Person> toListEntity(List<PersonRequestDTO> dtos);
    List<PersonResponseDTO> toListResponse(List<Person> entities);
}
