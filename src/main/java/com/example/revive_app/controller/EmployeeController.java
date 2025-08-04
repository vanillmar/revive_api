package com.example.revive_app.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.revive_app.data.Permissions;
import com.example.revive_app.data.dto.EmployeeRequestDTO;
import com.example.revive_app.data.dto.EmployeeResponseDTO;
import com.example.revive_app.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasAuthority('"+ Permissions.READ_EMPLOYEES +"')")
    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PreAuthorize("hasAuthority('"+ Permissions.READ_EMPLOYEE +"')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable UUID id) {
        Optional<EmployeeResponseDTO> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('" + Permissions.CREATE_EMPLOYEE + "')")
    @PostMapping
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeRequestDTO employee) {
        return employeeService.createEmployee(employee);
    }

    @PreAuthorize("hasAuthority('" + Permissions.CREATE_EMPLOYEES + "')")
    @PostMapping("/batch")
    public List<EmployeeResponseDTO> createEmployees(@RequestBody List<EmployeeRequestDTO> employees) {
        return employeeService.createEmployees(employees);
    }

    @PreAuthorize("hasAuthority('" + Permissions.UPDATE_EMPLOYEE + "')")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable UUID id, @RequestBody EmployeeRequestDTO employeeDetails) {
        Optional<EmployeeResponseDTO> updated = employeeService.updateEmployee(id, employeeDetails);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('" + Permissions.UPDATE_EMPLOYEES + "')")
    @PutMapping("/batch")
    public List<EmployeeResponseDTO> updateEmployees(@RequestBody List<EmployeeRequestDTO> employees) {
        return employeeService.updateEmployees(employees);
    }

    @PreAuthorize("hasAuthority('" + Permissions.DELETE_EMPLOYEE + "')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable UUID id) {
        boolean deleted = employeeService.deleteEmployee(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
