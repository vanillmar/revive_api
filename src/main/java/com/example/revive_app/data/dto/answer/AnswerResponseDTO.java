package com.example.revive_app.data.dto.answer;

import lombok.Data;

@Data
public class AnswerResponseDTO {
    private Long id;
    private Long examAttemptId;
    private Long questionId;
    private Integer selectedOptionIndex;
    private Boolean isCorrect;
    private Boolean isFinal;
}
