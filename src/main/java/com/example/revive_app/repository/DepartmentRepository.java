package com.example.revive_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.revive_app.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}

