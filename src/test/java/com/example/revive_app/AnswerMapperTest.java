/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.revive_app.data.dto.answer.AnswerRequestDTO;
import com.example.revive_app.data.dto.answer.AnswerResponseDTO;
import com.example.revive_app.data.mapper.AnswerMapper;
import com.example.revive_app.model.Answer;
import com.example.revive_app.model.ExamAttempt;
import com.example.revive_app.model.Question;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class AnswerMapperTest {

    private final AnswerMapper mapper = Mappers.getMapper(AnswerMapper.class);

    @Test
    void toResponse_shouldMapAnswerToResponseDTO() {
        // Arrange
        ExamAttempt examAttempt = new ExamAttempt();
        examAttempt.setId(1L);

        Question question = new Question();
        question.setId(2L);

        Answer answer = new Answer();
        answer.setId(3L);
        answer.setExamAttempt(examAttempt);
        answer.setQuestion(question);
        answer.setSelectedOptionIndex(1);
        answer.setIsCorrect(true);
        answer.setIsFinal(true);

        // Act
        AnswerResponseDTO response = mapper.toResponse(answer);

        // Assert
        assertNotNull(response);
        assertEquals(3L, response.getId());
        assertEquals(1L, response.getExamAttemptId());
        assertEquals(2L, response.getQuestionId());
        assertEquals(1, response.getSelectedOptionIndex());
        assertTrue(response.getIsCorrect());
        assertTrue(response.getIsFinal());
    }

    @Test
    void toResponseList_shouldMapListOfAnswersToListOfResponseDTOs() {
        // Arrange
        ExamAttempt examAttempt1 = new ExamAttempt();
        examAttempt1.setId(1L);

        Question question1 = new Question();
        question1.setId(2L);

        Answer answer1 = new Answer();
        answer1.setId(3L);
        answer1.setExamAttempt(examAttempt1);
        answer1.setQuestion(question1);
        answer1.setSelectedOptionIndex(1);
        answer1.setIsCorrect(true);
        answer1.setIsFinal(true);

        ExamAttempt examAttempt2 = new ExamAttempt();
        examAttempt2.setId(4L);

        Question question2 = new Question();
        question2.setId(5L);

        Answer answer2 = new Answer();
        answer2.setId(6L);
        answer2.setExamAttempt(examAttempt2);
        answer2.setQuestion(question2);
        answer2.setSelectedOptionIndex(2);
        answer2.setIsCorrect(false);
        answer2.setIsFinal(false);

        List<Answer> answers = Arrays.asList(answer1, answer2);

        // Act
        List<AnswerResponseDTO> responses = mapper.toResponseList(answers);

        // Assert
        assertNotNull(responses);
        assertEquals(2, responses.size());

        AnswerResponseDTO response1 = responses.get(0);
        assertEquals(3L, response1.getId());
        assertEquals(1L, response1.getExamAttemptId());
        assertEquals(2L, response1.getQuestionId());
        assertEquals(1, response1.getSelectedOptionIndex());
        assertTrue(response1.getIsCorrect());
        assertTrue(response1.getIsFinal());

        AnswerResponseDTO response2 = responses.get(1);
        assertEquals(6L, response2.getId());
        assertEquals(4L, response2.getExamAttemptId());
        assertEquals(5L, response2.getQuestionId());
        assertEquals(2, response2.getSelectedOptionIndex());
        assertFalse(response2.getIsCorrect());
        assertFalse(response2.getIsFinal());
    }

    @Test
    void toEntity_shouldMapRequestDTOExamAttemptAndQuestionToAnswerIgnoringIdAndIsCorrect() {
        // Arrange
        AnswerRequestDTO request = new AnswerRequestDTO();
        request.setSelectedOptionIndex(3);
        request.setIsFinal(true);

        ExamAttempt examAttempt = new ExamAttempt();
        examAttempt.setId(7L);

        Question question = new Question();
        question.setId(8L);

        // Act
        Answer answer = mapper.toEntity(request, examAttempt, question);

        // Assert
        assertNotNull(answer);
        assertNull(answer.getId()); // Ignored
        assertEquals(examAttempt, answer.getExamAttempt());
        assertEquals(7L, answer.getExamAttempt().getId());
        assertEquals(question, answer.getQuestion());
        assertEquals(8L, answer.getQuestion().getId());
        assertEquals(3, answer.getSelectedOptionIndex());
        assertNull(answer.getIsCorrect()); // Ignored
        assertTrue(answer.getIsFinal());
    }
}