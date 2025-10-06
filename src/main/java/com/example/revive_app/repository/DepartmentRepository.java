/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;

import com.example.revive_app.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
