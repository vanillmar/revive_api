/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.answer.AnswerRequestDTO;
import com.example.revive_app.data.dto.answer.AnswerResponseDTO;
import com.example.revive_app.model.Answer;
import com.example.revive_app.model.ExamAttempt;
import com.example.revive_app.model.Question;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    @Mapping(source = "examAttempt.id", target = "examAttemptId")
    @Mapping(source = "question.id", target = "questionId")
    AnswerResponseDTO toResponse(Answer answer);

    List<AnswerResponseDTO> toResponseList(List<Answer> answers);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "examAttempt", source = "examAttempt")
    @Mapping(target = "question", source = "question")
    @Mapping(target = "isCorrect", ignore = true)
    Answer toEntity(AnswerRequestDTO request, ExamAttempt examAttempt, Question question);
}