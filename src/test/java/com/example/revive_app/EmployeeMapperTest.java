package com.example.revive_app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.revive_app.data.dto.EmployeeRequestDTO;
import com.example.revive_app.data.dto.EmployeeResponseDTO;
import com.example.revive_app.data.mapper.EmployeeMapper;
import com.example.revive_app.model.Department;
import com.example.revive_app.model.Employee;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class EmployeeMapperTest {

  private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

  @Test
  void testToResponseDTO() {
    Employee employee = new Employee();
    employee.setId(UUID.randomUUID());
    employee.setFirstname("John");
    employee.setLastname("Doe");
    Department department = new Department();
    department.setId(1L);
    employee.setDepartment(department);

    EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(employee);
    assertNotNull(responseDTO);
    assertEquals(responseDTO.getFirstname(), "John");
    assertEquals(responseDTO.getLastname(), "Doe");
    assertEquals(responseDTO.getDepartment(), department.getId());
  }

  @Test
  void testToEntity() {
    EmployeeRequestDTO requestDTO = new EmployeeRequestDTO();
    requestDTO.setFirstname("Jane");
    requestDTO.setLastname("Smith");

    Employee employee = employeeMapper.toEntity(requestDTO);

    assertNotNull(employee);
    assertEquals(employee.getFirstname(), "Jane");
    assertEquals(employee.getLastname(), "Smith");
  }

  @Test
  void testToResponseDTOs() {
    Employee employee1 = new Employee();
    employee1.setFirstname("Alice");
    Employee employee2 = new Employee();
    employee2.setFirstname("Bob");

    List<EmployeeResponseDTO> responseDTOs =
        employeeMapper.toResponseDTOs(List.of(employee1, employee2));

    assertEquals(2, responseDTOs.size(), "Expected two DTOs in the list");
    assertEquals(responseDTOs.get(0).getFirstname(), "Alice");
    assertEquals(responseDTOs.get(1).getFirstname(), "Bob");
  }
}
