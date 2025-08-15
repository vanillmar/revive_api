package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.EmployeeRequestDTO;
import com.example.revive_app.data.dto.EmployeeResponseDTO;
import com.example.revive_app.model.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeResponseDTO toResponseDTO(Employee employee);

  Employee toEntity(EmployeeRequestDTO dto);
}
