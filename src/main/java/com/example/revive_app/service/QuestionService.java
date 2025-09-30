package com.example.revive_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.revive_app.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

import com.example.revive_app.model.Question;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Optional<Question> findById(String id) {
        return questionRepository.findById(id);
    }

    // public List<Question> findBySubjectName(String subject) {
    //     return questionRepository.findBySubjectName(subject);
    // }

    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public void deleteById(String id) {
        questionRepository.deleteById(id);
    }
}
