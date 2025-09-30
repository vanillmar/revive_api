package com.example.revive_app.repository;

import com.example.revive_app.model.ExamStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ExamStatusRepository extends JpaRepository<ExamStatus, Long> {
    Optional<ExamStatus> findByName(String name);
}
