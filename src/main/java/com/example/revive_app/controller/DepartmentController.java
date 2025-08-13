package com.example.revive_app.controller;

import java.util.List;

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
import com.example.revive_app.data.dto.DepartmentRequestDTO;
import com.example.revive_app.data.dto.DepartmentResponseDTO;
import com.example.revive_app.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")

public class DepartmentController {
    
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PreAuthorize("hasAuthority('" + Permissions.READ_DEPARTMENTS + "')")
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        List<DepartmentResponseDTO> departments = departmentService.getAllDepartments();
        if (departments.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(departments);
    }

    @PreAuthorize("hasAuthority('" + Permissions.READ_DEPARTMENT + "')")
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentResponseDTO department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @PreAuthorize("hasAuthority('" + Permissions.CREATE_DEPARTMENT + "')")
    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(
        @RequestBody DepartmentRequestDTO department) {
    DepartmentResponseDTO createdDepartment = departmentService.createDepartment(department);
        return ResponseEntity.status(201).body(createdDepartment);
    }

    @PreAuthorize("hasAuthority('" + Permissions.CREATE_DEPARTMENTS + "')")
    @PostMapping("/batch")
    public ResponseEntity<List<DepartmentResponseDTO>> createDepartments(
        @RequestBody List<DepartmentRequestDTO> departments) {
        List<DepartmentResponseDTO> createdDepartments = departmentService.createDepartments(departments);
        if (createdDepartments.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.status(201).body(createdDepartments);
    }

  @PreAuthorize("hasAuthority('" + Permissions.UPDATE_DEPARTMENT + "')")
  @PutMapping("/{id}")
  public ResponseEntity<DepartmentResponseDTO> updateDepartment(
      @PathVariable Long id, @RequestBody DepartmentRequestDTO departmentDetails) {
    DepartmentResponseDTO updatedDepartment = departmentService.updateDepartment(id, departmentDetails);
        return  ResponseEntity.status(201).body(updatedDepartment);
    }

  @PreAuthorize("hasAuthority('" + Permissions.UPDATE_DEPARTMENTS + "')")
  @PutMapping("/batch")
  public ResponseEntity<List<DepartmentResponseDTO>> updateDepartments(
      @RequestBody List<DepartmentRequestDTO> departments) {
    List<DepartmentResponseDTO> updatedDepartments = departmentService.updateDepartments(departments);
    if (updatedDepartments.isEmpty()) return ResponseEntity.noContent().build();
    return ResponseEntity.ok(updatedDepartments);
  }

  @PreAuthorize("hasAuthority('" + Permissions.DELETE_DEPARTMENT + "')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
    boolean deleted = departmentService.deleteEmployee(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }

}
