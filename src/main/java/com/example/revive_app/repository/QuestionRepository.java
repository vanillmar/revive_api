/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;

import com.example.revive_app.model.Question;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySubjectName(String name);
    @Query(value = """
            SELECT q FROM Question q
            WHERE CAST(q.question AS string) LIKE LOWER(CONCAT('%', :search, '%'))
            """)
    Page<Question> findByQuestionContainingIgnoreCase(String search, Pageable pageable);
}
