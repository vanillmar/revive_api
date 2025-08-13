package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.EmployeeRequestDTO;
import com.example.revive_app.data.dto.EmployeeResponseDTO;
import com.example.revive_app.data.dto.DepartmentResponseDTO;
import com.example.revive_app.model.Employee;
import com.example.revive_app.model.Department;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    // Convert from entity to response DTO
    @Mapping(source = "department", target = "department")
    EmployeeResponseDTO toResponseDTO(Employee employee);

    // Convert from entity list to response DTO list
    List<EmployeeResponseDTO> toResponseDTOList(List<Employee> employees);

    // Convert from request DTO to entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true) // We'll set it in service after fetching from DB
    Employee toEntity(EmployeeRequestDTO dto);

    // Mapping for nested department in response
    DepartmentResponseDTO toDepartmentResponseDTO(Department department);
}
