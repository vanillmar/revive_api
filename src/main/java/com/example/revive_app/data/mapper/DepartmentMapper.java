package com.example.revive_app.data.mapper;

import org.mapstruct.Mapper;

import com.example.revive_app.data.dto.DepartmentRequestDTO;
import com.example.revive_app.data.dto.DepartmentResponseDTO;
import com.example.revive_app.data.dto.EmployeeRequestDTO;
import com.example.revive_app.model.Department;
import com.example.revive_app.model.Employee;

import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
        // Convert from entity to response DTO
    @Mapping(source = "head", target = "head")
    @Mapping(source = "employees", target = "employees")
    DepartmentResponseDTO toResponseDTO(Department department);

    // Convert from entity list to DTO list
    List<DepartmentResponseDTO> toResponseDTOList(List<Department> departments);

    // Convert from request DTO to entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "head", ignore = true) // We'll set head in service after fetching employee
    Department toEntity(DepartmentRequestDTO dto);

    // Employee mapping for nested objects
    EmployeeRequestDTO toEmployeeRequestDTO(Employee employee);
    List<EmployeeRequestDTO> toEmployeeRequestDTOList(List<Employee> employees);
}
