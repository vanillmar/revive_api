package com.example.revive_app.repository;

import com.example.revive_app.model.ExamStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamStatusRepository extends JpaRepository<ExamStatus, Long> {
  Optional<ExamStatus> findByName(String name);
}
