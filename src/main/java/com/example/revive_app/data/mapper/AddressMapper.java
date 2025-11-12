/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.revive_app.model.Address;
import com.example.revive_app.model.Person;
import com.example.revive_app.request.AddressRequestDTO;
import com.example.revive_app.response.AddressResponseDTO;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "personId", target = "person", qualifiedByName = "idToPerson")
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Address toEntity(AddressRequestDTO dto);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "deleted", ignore = true)
    AddressResponseDTO toResponse(Address entity);

    List<Address> toListEntities(List<AddressRequestDTO> dtos);

    List<AddressResponseDTO> toResponseDTOs(List<Address> entities);

    @Named("idToPerson")
    default Person idToPerson(Long id) {
        if (id == null)
            return null;
        Person p = new Person();
        p.setId(id);
        return p;
    }
}
