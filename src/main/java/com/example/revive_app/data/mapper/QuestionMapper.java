/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.question.QuestionRequestDTO;
import com.example.revive_app.data.dto.question.QuestionResponseDTO;
import com.example.revive_app.model.Question;
import com.example.revive_app.model.Subject;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(source = "subject.name", target = "subjectName")
    QuestionResponseDTO toResponse(Question question);

    // List<Entity> → List<DTO>
    List<QuestionResponseDTO> toResponseList(List<Question> questions);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subject", source = "subject")
    Question toEntity(QuestionRequestDTO request, Subject subject);
}
