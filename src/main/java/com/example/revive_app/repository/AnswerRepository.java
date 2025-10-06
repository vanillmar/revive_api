/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;

import com.example.revive_app.model.Answer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByExamAttemptId(Long examAttemptId);

    Optional<Answer> findByExamAttemptIdAndQuestionId(Long examAttemptId, Long questionId);
}
