package com.example.revive_app.repository;

import com.example.revive_app.model.Employee;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
  List<Employee> findEmployeesByDepartmentId(Long id);
}
