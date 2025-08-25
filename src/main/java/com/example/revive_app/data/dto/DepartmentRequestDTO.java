package com.example.revive_app.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequestDTO {
  private Long id;

  @NotBlank(message = "Department name is required")
  @Size(min = 4, message = "Department must at least have 4 characters")
  private String name;

  @NotBlank(message = "Description is required")
  private String description;

  @NotBlank(message = "Head of department is required")
  private EmployeeRequestDTO head;

  private List<EmployeeRequestDTO> employees;
}
