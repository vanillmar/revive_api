/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.ContactInfoRequestDTO;
import com.example.revive_app.data.dto.ContactInfoResponseDTO;
import com.example.revive_app.model.ContactInfo;
import com.example.revive_app.model.Person;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "personId", target = "person", qualifiedByName = "idToPerson")
    @Mapping(target = "deleted", ignore = true)
    ContactInfo toEntity(ContactInfoRequestDTO dto);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "deleted", ignore = true)
    ContactInfoResponseDTO toResponse(ContactInfo entity);

    List<ContactInfo> toListEntity(List<ContactInfoRequestDTO> dtos);
    List<ContactInfoResponseDTO> toListResponse(List<ContactInfo> entities);

    @Named("idToPerson")
    default Person idToPerson(Long id) {
        if (id == null)
            return null;
        Person p = new Person();
        p.setId(id);
        return p;
    }
}
