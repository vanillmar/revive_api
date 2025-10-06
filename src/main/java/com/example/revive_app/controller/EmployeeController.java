/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import com.example.revive_app.data.Permissions;
import com.example.revive_app.data.dto.EmployeeRequestDTO;
import com.example.revive_app.data.dto.EmployeeResponseDTO;
import com.example.revive_app.service.EmployeeService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasAuthority('" + Permissions.READ_EMPLOYEES + "')")
    @GetMapping("/department/{id}")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByDepartmentId(@PathVariable Long id) {
        List<EmployeeResponseDTO> employees = employeeService.getEmployeesByDepartmentId(id);
        if (employees.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(employees);
    }

    @PreAuthorize("hasAuthority('" + Permissions.READ_EMPLOYEES + "')")
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.getAllEmployees();
        if (employees.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(employees);
    }

    @PreAuthorize("hasAuthority('" + Permissions.READ_EMPLOYEE + "')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable UUID id) {
        Optional<EmployeeResponseDTO> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('" + Permissions.CREATE_EMPLOYEE + "')")
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO employee) {
        EmployeeResponseDTO createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(201).body(createdEmployee);
    }

    @PreAuthorize("hasAuthority('" + Permissions.CREATE_EMPLOYEES + "')")
    @PostMapping("/batch")
    public ResponseEntity<List<EmployeeResponseDTO>> createEmployees(@RequestBody List<EmployeeRequestDTO> employees) {
        List<EmployeeResponseDTO> createdEmployees = employeeService.createEmployees(employees);
        if (createdEmployees.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.status(201).body(createdEmployees);
    }

    @PreAuthorize("hasAuthority('" + Permissions.UPDATE_EMPLOYEE + "')")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable UUID id,
            @RequestBody EmployeeRequestDTO employeeDetails) {
        Optional<EmployeeResponseDTO> updated = employeeService.updateEmployee(id, employeeDetails);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('" + Permissions.UPDATE_EMPLOYEES + "')")
    @PutMapping("/batch")
    public ResponseEntity<List<EmployeeResponseDTO>> updateEmployees(@RequestBody List<EmployeeRequestDTO> employees) {
        List<EmployeeResponseDTO> updatedEmployees = employeeService.updateEmployees(employees);
        if (updatedEmployees.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(updatedEmployees);
    }

    @PreAuthorize("hasAuthority('" + Permissions.DELETE_EMPLOYEE + "')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable UUID id) {
        boolean deleted = employeeService.deleteEmployee(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
