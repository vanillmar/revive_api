/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.model.Exam;
import com.example.revive_app.repository.ExamRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    public Optional<Exam> findBySubject(String subject) {
        return examRepository.findAll().stream().filter(exam -> exam.getSubject().getName().equals(subject))
                .findFirst();
    }

    public boolean exists(long id) {
        return examRepository.existsById(id);
    }

    public boolean existsBySubject(String subject) {
        return examRepository.findAll().stream().anyMatch(exam -> exam.getSubject().getName().equals(subject));
    }

    public boolean existsByTitle(String title) {
        return examRepository.findAll().stream().anyMatch(exam -> exam.getTitle().equals(title));
    }

    public Exam create(Exam exam) {
        if (exam.getId() != null && examRepository.existsById(exam.getId())) {
            throw new IllegalArgumentException("Exam with id " + exam.getId() + " already exists");
        }
        if (exam.getSubject() == null) {
            throw new IllegalArgumentException("Subject is required");
        }
        if (existsByTitle(exam.getTitle())) {
            throw new IllegalArgumentException("Exam with title " + exam.getTitle() + " already exists");
        }
        return examRepository.save(exam);
    }

    public Exam update(String subject, Exam update) {
        if (!existsBySubject(subject)) {
            throw new IllegalArgumentException("Exam with subject: " + subject + " does not exist");
        }
        if (!exists(update.getId())) {
            throw new IllegalArgumentException("Exam with subject: " + subject + " does not exist");
        }

        return examRepository.save(update);
    }

    public boolean delete(long id) {
        return examRepository.findAll().removeIf(exam -> exam.getId() == id);
    }

    public boolean deleteBySubject(String subject) {
        return examRepository.findAll().removeIf(exam -> exam.getSubject().getName().equals(subject));
    }

    public Long getTotalExams() {
        return examRepository.count();
    }
}
