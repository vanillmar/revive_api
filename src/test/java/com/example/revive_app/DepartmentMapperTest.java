package com.example.revive_app;

import com.example.revive_app.data.dto.DepartmentRequestDTO;
import com.example.revive_app.data.dto.DepartmentResponseDTO;
import com.example.revive_app.data.mapper.DepartmentMapper;
import com.example.revive_app.model.Department;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DepartmentMapperTest {

    private final DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);

    @Test
    void testToResponseDTO() {
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        DepartmentResponseDTO responseDTO = departmentMapper.toResponseDTO(department);

        assertNotNull(responseDTO);
        assertEquals(responseDTO.getName(), "HR");
    }

    @Test
    void testToEntity() {
        DepartmentRequestDTO requestDTO = new DepartmentRequestDTO();
        requestDTO.setName("Finance");

        Department department = departmentMapper.toEntity(requestDTO);

        assertNotNull(department);
        assertEquals(department.getName(),"Finance");
    }

    @Test
    void testToResponseDTOs() {
        Department department1 = new Department();
        department1.setName("IT");
        Department department2 = new Department();
        department2.setName("Marketing");

        List<DepartmentResponseDTO> responseDTOs = departmentMapper.toResponseDTOs(List.of(department1, department2));

        assertEquals(responseDTOs.size(), 2);
        assertEquals(responseDTOs.get(0).getName(), "IT");
        assertEquals(responseDTOs.get(1).getName(), "Marketing");
    }
}