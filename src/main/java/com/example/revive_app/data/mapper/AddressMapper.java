/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.model.Address;
import com.example.revive_app.request.AddressRequestDTO;
import com.example.revive_app.response.AddressResponseDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "active", ignore = true)
    @IgnoreAuditMapping
    Address toEntity(AddressRequestDTO dto);

    List<Address> toListEntities(List<AddressRequestDTO> dtos);

    @Mapping(target = "personId", source = "person.id")
    AddressResponseDTO toResponse(Address entity);

    List<AddressResponseDTO> toResponseDTOs(List<Address> entities);
}
