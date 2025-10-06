/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.EmployeeRequestDTO;
import com.example.revive_app.data.dto.EmployeeResponseDTO;
import com.example.revive_app.model.Employee;
import java.util.List;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "roles", ignore = true)
    @Mapping(source = "department.id", target = "department")
    EmployeeResponseDTO toResponseDTO(Employee employee);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Employee toEntity(EmployeeRequestDTO dto);

    List<EmployeeResponseDTO> toResponseDTOs(List<Employee> employees);
}
