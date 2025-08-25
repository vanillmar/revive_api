package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.DepartmentRequestDTO;
import com.example.revive_app.data.dto.DepartmentResponseDTO;
import com.example.revive_app.model.Department;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(
    componentModel = "spring",
    uses = {EmployeeMapper.class})
public interface DepartmentMapper {
  @Mapping(target = "employees", ignore = true) // Assuming you want to ignore employees in the request DTO
  DepartmentResponseDTO toResponseDTO(Department department);

  List<DepartmentResponseDTO> toResponseDTOs(List<Department> departments);

  Department toEntity(DepartmentRequestDTO dto);

  List<Department> toListEntities(List<DepartmentRequestDTO> dtos);
}
