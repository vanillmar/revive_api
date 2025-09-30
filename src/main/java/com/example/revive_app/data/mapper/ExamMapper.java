package com.example.revive_app.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.revive_app.data.dto.ExamRequestDTO;
import com.example.revive_app.data.dto.ExamResponseDTO;
import com.example.revive_app.data.dto.ExamStatusResponseDTO;
import com.example.revive_app.model.Exam;
import com.example.revive_app.model.ExamStatus;

@Mapper(componentModel = "spring", uses = {ExamStatusMapper.class})
public interface ExamMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", source = "statusId", qualifiedByName = "idToExamStatus")
  public Exam toEntity(ExamRequestDTO dto);
  public List<Exam> toEntity(List<ExamRequestDTO> dtoList);

  @Mapping(source = "status", target = "examStatus", qualifiedByName = "examStatusToId")
  public ExamResponseDTO toResponseDTO(Exam exam);
  public List<ExamResponseDTO> toResponseDTOs(List<Exam> exams);

  @Named("idToExamStatus")
  default ExamStatus idToExamStatus(Long id) {
    if (id == null) return null;
    ExamStatus s = new ExamStatus();
    s.setId(id);
    return s;
  }
  @Named("examStatusToId")
  default ExamStatusResponseDTO examStatusToId(ExamStatus status) {
    if (status == null) return null;
    ExamStatusResponseDTO s = new ExamStatusResponseDTO();
    s.setId(status.getId());
    s.setName(status.getName());
    s.setDescription(status.getDescription());
    return s;
  }
}
