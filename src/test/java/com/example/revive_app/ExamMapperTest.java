/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.revive_app.data.dto.ExamRequestDTO;
import com.example.revive_app.data.dto.ExamResponseDTO;
import com.example.revive_app.data.dto.ExamStatusResponseDTO;
import com.example.revive_app.data.mapper.ExamMapper;
import com.example.revive_app.model.Exam;
import com.example.revive_app.model.ExamStatus;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ExamMapperTest {

    private final ExamMapper mapper = Mappers.getMapper(ExamMapper.class);

    @Test
    void testToEntity_setsStatusFromId() {
        ExamRequestDTO dto = new ExamRequestDTO();
        dto.setStatusId(5L);

        Exam exam = mapper.toEntity(dto);

        assertNotNull(exam);
        assertNotNull(exam.getStatus());
        assertEquals(5L, exam.getStatus().getId());
    }

    @Test
    void testToResponseDTO_includesExamStatus() {
        Exam exam = new Exam();
        exam.setId(10L);
        ExamStatus status = new ExamStatus();
        status.setId(7L);
        status.setName("SCHEDULed");
        status.setDescription("Scheduled exam");
        exam.setStatus(status);

        ExamResponseDTO dto = mapper.toResponseDTO(exam);

        assertNotNull(dto);
        ExamStatusResponseDTO s = dto.getExamStatus();
        assertNotNull(s);
        assertEquals(7L, s.getId());
        assertEquals("SCHEDULed", s.getName());
        assertEquals("Scheduled exam", s.getDescription());
    }

    @Test
    void testToResponseDTOs_mapsList() {
        Exam e1 = new Exam();
        e1.setId(1L);
        Exam e2 = new Exam();
        e2.setId(2L);

        List<ExamResponseDTO> dtos = mapper.toResponseDTOs(List.of(e1, e2));

        assertEquals(2, dtos.size());
        assertEquals(1L, dtos.get(0).getId());
        assertEquals(2L, dtos.get(1).getId());
    }
}
