/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;

import com.example.revive_app.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
