package com.example.revive_app.data.dto;

import java.util.List;

public class DepartmentResponseDTO {
    private Long id;
    private String name;
    private String description;
    private EmployeeResponseDTO head;
    private List<DepartmentResponseDTO> employees;
}
