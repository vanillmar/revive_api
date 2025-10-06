/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.ExamRequestDTO;
import com.example.revive_app.data.dto.ExamResponseDTO;
import com.example.revive_app.data.dto.ExamStatusResponseDTO;
import com.example.revive_app.data.dto.SubjectResponseDTO;
import com.example.revive_app.model.Exam;
import com.example.revive_app.model.ExamStatus;
import com.example.revive_app.model.Subject;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {ExamStatusMapper.class})
public interface ExamMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "statusId", qualifiedByName = "idToExamStatus")
    @Mapping(target = "subject", source = "subjectId", qualifiedByName = "idToSubject")
    public Exam toEntity(ExamRequestDTO dto);

    public List<Exam> toEntity(List<ExamRequestDTO> dtoList);

    @Mapping(source = "status", target = "examStatus", qualifiedByName = "examStatusToId")
    @Mapping(source = "subject", target = "subject", qualifiedByName = "subjectToId")
    public ExamResponseDTO toResponseDTO(Exam exam);

    public List<ExamResponseDTO> toResponseDTOs(List<Exam> exams);

    @Named("idToSubject")
    default Subject idToSubject(Long id) {
        if (id == null)
            return null;
        Subject s = new Subject();
        s.setId(id);
        return s;
    }

    @Named("subjectToId")
    default SubjectResponseDTO subjectToId(Subject subject) {
        if (subject == null)
            return null;
        if (subject.getId() == null)
            return null;
        SubjectResponseDTO s = new SubjectResponseDTO();
        s.setId(subject.getId());
        s.setName(subject.getName());
        return s;
    }

    @Named("idToExamStatus")
    default ExamStatus idToExamStatus(Long id) {
        if (id == null)
            return null;
        ExamStatus s = new ExamStatus();
        s.setId(id);
        return s;
    }

    @Named("examStatusToId")
    default ExamStatusResponseDTO examStatusToId(ExamStatus status) {
        if (status == null)
            return null;
        ExamStatusResponseDTO s = new ExamStatusResponseDTO();
        s.setId(status.getId());
        s.setName(status.getName());
        s.setDescription(status.getDescription());
        return s;
    }
}
