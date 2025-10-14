/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.subject.SubjectRequestDTO;
import com.example.revive_app.data.dto.subject.SubjectResponseDTO;
import com.example.revive_app.model.Subject;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "questions", ignore = true)
    Subject toEntity(SubjectRequestDTO request);

    SubjectResponseDTO toResponse(Subject subject);
    List<SubjectResponseDTO> toResponseList(List<Subject> subjects);

}
