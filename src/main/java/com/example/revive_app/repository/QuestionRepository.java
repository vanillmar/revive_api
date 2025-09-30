package com.example.revive_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.revive_app.model.Question;

public interface QuestionRepository extends JpaRepository<Question, String> {
}