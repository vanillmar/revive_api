/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.model.ContactInfo;
import com.example.revive_app.request.ContactInfoRequestDTO;
import com.example.revive_app.response.ContactInfoResponseDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "active", ignore = true)
    @IgnoreAuditMapping
    ContactInfo toEntity(ContactInfoRequestDTO dto);

    List<ContactInfo> toListEntity(List<ContactInfoRequestDTO> dtos);

    @Mapping(target = "personId", source = "person.id")
    ContactInfoResponseDTO toResponse(ContactInfo entity);

    List<ContactInfoResponseDTO> toListResponse(List<ContactInfo> entities);
}
