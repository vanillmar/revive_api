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
    private String subject;
    private String title;
    private String result;
    // send the status id on create/update requests
    private Long statusId;
}