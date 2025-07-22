package com.example.revive_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.revive_app.model.Employee;
import com.example.revive_app.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Create new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Create multiple employees
    @PostMapping("/batch")
    public List<Employee> createEmployees(@RequestBody List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    // Update employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(employeeDetails.getFirstName());
                    employee.setLastName(employeeDetails.getLastName());
                    employee.setEmail(employeeDetails.getEmail());
                    Employee updated = employeeRepository.save(employee);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update multiple employees
    @PutMapping("/batch")
    public List<Employee> updateEmployees(@RequestBody List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    // Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.delete(employee);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

