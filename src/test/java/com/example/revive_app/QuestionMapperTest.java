/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app;

import static org.junit.jupiter.api.Assertions.*;

import com.example.revive_app.data.dto.question.QuestionRequestDTO;
import com.example.revive_app.data.dto.question.QuestionResponseDTO;
import com.example.revive_app.data.mapper.QuestionMapper;
import com.example.revive_app.model.Question;
import com.example.revive_app.model.Subject;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class QuestionMapperTest {

    private final QuestionMapper mapper = Mappers.getMapper(QuestionMapper.class);

    @Test
    void toResponse_shouldMapQuestionToResponseDTO() {
        // Arrange
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Mathematics");

        Question question = new Question();
        question.setId(1L);
        question.setQuestion("What is 2 + 2?");
        question.setSubject(subject);

        // Act
        QuestionResponseDTO response = mapper.toResponse(question);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("What is 2 + 2?", response.getQuestion());
        assertEquals("Mathematics", response.getSubjectName());
    }

    @Test
    void toResponseList_shouldMapListOfQuestionsToListOfResponseDTOs() {
        // Arrange
        Subject subject1 = new Subject();
        subject1.setId(1L);
        subject1.setName("Mathematics");

        Question question1 = new Question();
        question1.setId(1L);
        question1.setQuestion("What is 2 + 2?");
        question1.setSubject(subject1);

        Subject subject2 = new Subject();
        subject2.setId(2L);
        subject2.setName("Science");

        Question question2 = new Question();
        question2.setId(2L);
        question2.setQuestion("What is H2O?");
        question2.setSubject(subject2);

        List<Question> questions = Arrays.asList(question1, question2);

        // Act
        List<QuestionResponseDTO> responses = mapper.toResponseList(questions);

        // Assert
        assertNotNull(responses);
        assertEquals(2, responses.size());

        QuestionResponseDTO response1 = responses.get(0);
        assertEquals(1L, response1.getId());
        assertEquals("What is 2 + 2?", response1.getQuestion());
        assertEquals("Mathematics", response1.getSubjectName());

        QuestionResponseDTO response2 = responses.get(1);
        assertEquals(2L, response2.getId());
        assertEquals("What is H2O?", response2.getQuestion());
        assertEquals("Science", response2.getSubjectName());
    }

    @Test
    void toEntity_shouldMapRequestDTOAndSubjectToQuestionIgnoringId() {
        // Arrange
        QuestionRequestDTO request = new QuestionRequestDTO();
        request.setQuestion("What is the capital of France?");

        Subject subject = new Subject();
        subject.setId(3L);
        subject.setName("Geography");

        // Act
        Question question = mapper.toEntity(request, subject);

        // Assert
        assertNotNull(question);
        assertNull(question.getId()); // Ignored
        assertEquals("What is the capital of France?", question.getQuestion());
        assertEquals(subject, question.getSubject());
        assertEquals(3L, question.getSubject().getId());
        assertEquals("Geography", question.getSubject().getName());
    }
}