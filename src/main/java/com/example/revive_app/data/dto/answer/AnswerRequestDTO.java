package com.example.revive_app.data.dto.answer;

import lombok.Data;

@Data
public class AnswerRequestDTO {
    private Integer selectedOptionIndex;
    private Boolean isFinal;
}