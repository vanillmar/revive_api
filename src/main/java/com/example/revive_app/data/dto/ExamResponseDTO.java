package com.example.revive_app.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class ExamResponseDTO {
    private Long id;
    private String subject;
    private String title;
    private String result;
    private ExamStatusResponseDTO examStatus;
}