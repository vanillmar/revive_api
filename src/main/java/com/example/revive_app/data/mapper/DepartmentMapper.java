package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.DepartmentRequestDTO;
import com.example.revive_app.data.dto.DepartmentResponseDTO;
import com.example.revive_app.model.Department;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {EmployeeMapper.class})
public interface DepartmentMapper {
  DepartmentResponseDTO toResponseDTO(Department department);

  List<DepartmentResponseDTO> toResponseDTOs(List<Department> departments);

  Department toEntity(DepartmentRequestDTO dto);

  List<Department> toListEntities(List<DepartmentRequestDTO> dtos);
}
