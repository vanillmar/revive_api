package com.example.revive_app.service;

import com.example.revive_app.data.dto.DepartmentRequestDTO;
import com.example.revive_app.data.dto.DepartmentResponseDTO;
import com.example.revive_app.data.mapper.DepartmentMapper;
import com.example.revive_app.model.Department;
import com.example.revive_app.repository.DepartmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
  private final DepartmentRepository departmentRepository;
  private final DepartmentMapper departmentMapper;

  @Autowired
  public DepartmentService(
      DepartmentRepository departmentRepository,
      DepartmentMapper departmentMapper) {
    this.departmentRepository = departmentRepository;
    this.departmentMapper = departmentMapper;
  }

  public List<DepartmentResponseDTO> getAllDepartments() {
    return departmentMapper.toResponseDTOs(departmentRepository.findAll());
  }

  public DepartmentResponseDTO getDepartmentById(Long id) {
    Department department =
        departmentRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Department was not found."));
    return departmentMapper.toResponseDTO(department);
  }

  public DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto) {
    Department department = departmentMapper.toEntity(dto);
    department = departmentRepository.save(department);
    return departmentMapper.toResponseDTO(department);
  }

  public List<DepartmentResponseDTO> createDepartments(List<DepartmentRequestDTO> dtos) {
    List<Department> updatedDepartments = dtos.stream().map(departmentMapper::toEntity).toList();
    List<Department> savedDepartments = departmentRepository.saveAll(updatedDepartments);
    return savedDepartments.stream().map(departmentMapper::toResponseDTO).toList();
  }

  public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO dto) {
    Department department = departmentMapper.toEntity(dto);
    departmentRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Department not found."));
    department = departmentRepository.save(department);
    return departmentMapper.toResponseDTO(department);
  }

  public List<DepartmentResponseDTO> updateDepartments(List<DepartmentRequestDTO> dtos) {
    List<Department> updatedDepartments = dtos.stream().map(departmentMapper::toEntity).toList();
    List<Department> savedDepartments = departmentRepository.saveAll(updatedDepartments);
    return savedDepartments.stream().map(departmentMapper::toResponseDTO).toList();
  }

  public boolean deleteEmployee(long id) {
    return departmentRepository
        .findById(id)
        .map(
            employee -> {
              departmentRepository.delete(employee);
              return true;
            })
        .orElse(false);
  }
}
