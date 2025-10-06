package com.example.revive_app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.revive_app.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
}
