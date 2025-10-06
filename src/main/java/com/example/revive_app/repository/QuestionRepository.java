/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;

import com.example.revive_app.model.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySubjectName(String name);
}
