/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app;

import static org.junit.jupiter.api.Assertions.*;

import com.example.revive_app.data.dto.subject.SubjectRequestDTO;
import com.example.revive_app.data.dto.subject.SubjectResponseDTO;
import com.example.revive_app.data.mapper.SubjectMapper;
import com.example.revive_app.model.ExamStatus;
import com.example.revive_app.model.Subject;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class SubjectMapperTest {

    private final SubjectMapper mapper = Mappers.getMapper(SubjectMapper.class);

    @Test
    void toEntity_shouldMapRequestToEntityIgnoringCertainFields() {
        SubjectRequestDTO request = new SubjectRequestDTO();
        request.setName("Mathematics");
        request.setDescription("Basic math concepts");
        request.setCode("MATH101");

        Subject subject = mapper.toEntity(request);

        assertNull(subject.getId());
        assertEquals("Mathematics", subject.getName());
        assertEquals("Basic math concepts", subject.getDescription());
        assertEquals("MATH101", subject.getCode());
        assertTrue(subject.getQuestions().isEmpty());
    }

    @Test
    void toResponse_shouldMapEntityToResponseIncludingStatusId() {
        ExamStatus status = new ExamStatus();
        status.setId(1L);

        Subject subject = new Subject(1L, "Mathematics", "Basic math concepts", "MATH101");

        SubjectResponseDTO response = mapper.toResponse(subject);

        assertEquals(1L, response.getId());
        assertEquals("Mathematics", response.getName());
        assertEquals("Basic math concepts", response.getDescription());
        assertEquals("MATH101", response.getCode());
    }

    @Test
    void toResponse_shouldHandleNullStatus() {
        Subject subject = new Subject(1L, "Mathematics", "Basic math concepts", "MATH101");

        SubjectResponseDTO response = mapper.toResponse(subject);

        assertEquals(1L, response.getId());
        assertEquals("Mathematics", response.getName());
        assertEquals("Basic math concepts", response.getDescription());
        assertEquals("MATH101", response.getCode());
    }
}