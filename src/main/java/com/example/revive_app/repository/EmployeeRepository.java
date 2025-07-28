package com.example.revive_app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.revive_app.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    // JpaRepository provides CRUD and finder methods out of the box
    // You can add custom query methods here if needed
}
