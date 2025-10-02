package com.example.revive_app.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExamResponseDTO {
  private Long id;
  private SubjectResponseDTO subject;
  private String title;
  private String result;
  private int timeLimit;
  private int passMark;
  private ExamStatusResponseDTO examStatus;
}
