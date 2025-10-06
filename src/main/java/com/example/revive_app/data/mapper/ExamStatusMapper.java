/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.ExamStatusResponseDTO;
import com.example.revive_app.model.ExamStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamStatusMapper {
    ExamStatusResponseDTO toResponseDTO(ExamStatus status);
}
