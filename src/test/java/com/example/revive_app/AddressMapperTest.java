/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.revive_app.data.dto.AddressRequestDTO;
import com.example.revive_app.data.dto.AddressResponseDTO;
import com.example.revive_app.data.mapper.AddressMapper;
import com.example.revive_app.model.Address;
import com.example.revive_app.model.Employee;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AddressMapperTest {
    private final AddressMapper mapper = Mappers.getMapper(AddressMapper.class);

    @Test
    void testToDTO() {

        // Arrange
        UUID employeeId = UUID.randomUUID();
        Employee employee = new Employee("test_user", "1123", "stc@test.com", "test", "test");
        employee.setId(employeeId);

        Address address = new Address();
        address.setId(1L);
        address.setStreet("123 Main St");
        address.setCity("Luanda");
        address.setState("Luanda Province");
        address.setZipCode("1000");
        address.setEmployee(employee);

        // Act
        AddressResponseDTO dto = mapper.toResponseDTO(address);
        // Assert
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("123 Main St", dto.getStreet());
        assertEquals("Luanda", dto.getCity());
        assertEquals("Luanda Province", dto.getState());
        assertEquals("1000", dto.getZipCode());
        assertEquals(employeeId, dto.getEmployeeId());
    }

    @Test
    void testToEntity() {
        // Arrange
        UUID employeeId = UUID.randomUUID();
        AddressRequestDTO dto = new AddressRequestDTO(1L, "123 Main St", "Luanda", "Luanda Province", "1000",
                employeeId);

        // Act
        Address address = mapper.toEntity(dto);

        // Assert
        assertNotNull(address);
        assertEquals(1L, address.getId());
        assertEquals("123 Main St", address.getStreet());
        assertEquals("Luanda", address.getCity());
        assertEquals("Luanda Province", address.getState());
        assertEquals("1000", address.getZipCode());
        assertNotNull(address.getEmployee());
        assertEquals(employeeId, address.getEmployee().getId());
    }
}
