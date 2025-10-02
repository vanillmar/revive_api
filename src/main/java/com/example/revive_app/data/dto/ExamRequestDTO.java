package com.example.revive_app.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamRequestDTO {
  private Long subjectId;
  private String title;
  private String result;
  private int timeLimit;
  private int passMark;
  // send the status id on create/update requests
  private Long statusId;
}
