/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.dto.question;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionResponseDTO {
    private Long id;
    private String question;
    private List<String> options;
    private int answerIndex;
    private String subjectName; // 👈 Display readable subject info
}
