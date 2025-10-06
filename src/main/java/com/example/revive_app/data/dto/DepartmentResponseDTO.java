/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseDTO {
    private Long id;
    private String name;
    private String description;
    private EmployeeResponseDTO head;
    private List<EmployeeResponseDTO> employees;
}
