/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.model.Subject;
import com.example.revive_app.repository.SubjectRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject findById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + id));
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public boolean existsById(Long id) {
        return subjectRepository.existsById(id);
    }

    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }

    public Long getTotalSubjects() {
        return subjectRepository.count();
    }

}
