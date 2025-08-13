package com.example.revive_app.data.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequestDTO {
    private Long id;

    @NotBlank(message = "Department name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Head of department is required")
    private EmployeeRequestDTO head;
    
    private List<EmployeeRequestDTO> employees;
}
