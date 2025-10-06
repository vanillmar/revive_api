/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.model.Question;
import com.example.revive_app.repository.QuestionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    // public List<Question> findBySubjectName(String subject) {
    // return questionRepository.findBySubjectName(subject);
    // }

    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }

    public List<Question> findBySubjectName(String subject) {
        return questionRepository.findBySubjectName(subject);
    }
}
