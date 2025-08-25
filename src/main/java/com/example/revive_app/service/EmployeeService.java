package com.example.revive_app.service;

import com.example.revive_app.data.dto.EmployeeRequestDTO;
import com.example.revive_app.data.dto.EmployeeResponseDTO;
import com.example.revive_app.model.Employee;
import com.example.revive_app.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
    this.employeeRepository = employeeRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public List<EmployeeResponseDTO> getAllEmployees() {
    return employeeRepository.findAll().stream().map(this::toDto).toList();
  }

  public Optional<EmployeeResponseDTO> getEmployeeById(UUID id) {
    return employeeRepository.findById(id).map(this::toDto);
  }

  public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employee) {
    return toDto(employeeRepository.save(toEntity(employee)));
  }

  public List<EmployeeResponseDTO> createEmployees(List<EmployeeRequestDTO> employees) {
    return employeeRepository.saveAll(employees.stream().map(this::toEntity).toList()).stream()
        .map(this::toDto)
        .toList();
  }

  public Optional<EmployeeResponseDTO> updateEmployee(UUID id, EmployeeRequestDTO employeeDetails) {
    return employeeRepository
        .findById(id)
        .map(
            employee -> {
              employee.setUsername(employeeDetails.getUsername());
              employee.setEmail(employeeDetails.getEmail());

              String password =
                  employeeDetails.getPassword() != null && !employeeDetails.getPassword().isEmpty()
                      ? passwordEncoder.encode(employeeDetails.getPassword())
                      : employee.getPassword();

              employee.setPassword(password);
              employee.setFirstname(employeeDetails.getFirstname());
              employee.setLastname(employeeDetails.getLastname());
              return toDto(employeeRepository.save(employee));
            });
  }

  public List<EmployeeResponseDTO> updateEmployees(List<EmployeeRequestDTO> employees) {
    return employeeRepository.saveAll(employees.stream().map(this::toEntity).toList()).stream()
        .map(this::toDto)
        .toList();
  }

  public boolean deleteEmployee(UUID id) {
    return employeeRepository
        .findById(id)
        .map(
            employee -> {
              employeeRepository.delete(employee);
              return true;
            })
        .orElse(false);
  }

  private EmployeeResponseDTO toDto(Employee employee) {
    return EmployeeResponseDTO.builder()
        .id(employee.getId())
        .username(employee.getUsername())
        .email(employee.getEmail())
        .firstname(employee.getFirstname())
        .lastname(employee.getLastname())
        .enabled(employee.isEnabled())
        .roles(employee.getRoles())
        .build();
  }

  private Employee toEntity(EmployeeRequestDTO employeeRequest) {
    Employee employee =
        new Employee(
            employeeRequest.getUsername(),
            passwordEncoder.encode(employeeRequest.getPassword()),
            employeeRequest.getEmail(),
            employeeRequest.getFirstname(),
            employeeRequest.getLastname());
    employee.setUsername(employeeRequest.getUsername());
    employee.setEnabled(employeeRequest.isEnabled()); // Default to enabled
    return employee;
  }

  public List<EmployeeResponseDTO> getEmployeesByDepartmentId(Long id) {
    List<Employee> employees = employeeRepository.findEmployeesByDepartmentId(id);
    return employees.stream().map(this::toDto).toList();
  }
}
