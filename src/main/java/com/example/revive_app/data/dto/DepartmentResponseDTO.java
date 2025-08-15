package com.example.revive_app.data.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class DepartmentResponseDTO {
  private Long id;
  private String name;
  private String description;
  private EmployeeResponseDTO head;
  private List<EmployeeResponseDTO> employees;
}
