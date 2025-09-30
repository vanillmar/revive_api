package com.example.revive_app.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.revive_app.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Exam findBySubject(String subject);
}
