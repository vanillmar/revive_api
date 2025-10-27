/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.model.Question;
import com.example.revive_app.repository.QuestionRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with ID: " + id));
    }

    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public Question update(Long id, Question question) {
        if (!existsById(id)) {
            throw new IllegalArgumentException("Question with id: " + id.toString() + " does not exist");
        }
        return questionRepository.save(question);
    }

    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }

    public List<Question> findBySubjectName(String subject) {
        return questionRepository.findBySubjectName(subject);
    }

    public boolean existsById(Long id) {
        return questionRepository.existsById(id);
    }

    public Page<Question> findAllWithFilters(String search, Pageable pageable) {
        if (search == null || search.isEmpty()) {
            return questionRepository.findAll(pageable);
        }
        return questionRepository.findByQuestionContainingIgnoreCase(search, pageable);
    }

    public Long getTotalQuestions() {
        return questionRepository.count();
    }
}
