package com.example.revive_app.service;

import com.example.revive_app.model.Exam;
import com.example.revive_app.repository.ExamRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class ExamService {
    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;       
    }

    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    public Optional<Exam> findBySubject(String subject) {
        return examRepository.findAll().stream()
                .filter(exam -> exam.getSubject().equals(subject))
                .findFirst();
    }

    public boolean exists(long id) {
        return examRepository.existsById(id);
    }
 
    public boolean existsBySubject(String subject) {
        return examRepository.findAll().stream()
                .anyMatch(exam -> exam.getSubject().equals(subject));
    }
    public boolean existsByTitle(String title) {
        return examRepository.findAll().stream()
                .anyMatch(exam -> exam.getTitle().equals(title));
    }

    public Exam create(Exam exam) {
        if (exam.getId() != null && examRepository.existsById(exam.getId())) {
            throw new IllegalArgumentException("Exam with id " + exam.getId() + " already exists");
        }
        if (exam.getSubject() == null || exam.getSubject().isBlank()) {
            throw new IllegalArgumentException("Subject is required");
        }
        if (existsByTitle(exam.getTitle())) {
            throw new IllegalArgumentException("Exam with title " + exam.getTitle() + " already exists");
        }   
        Exam savedExam = examRepository.save(exam);
        return savedExam;
    }

    public Exam update(String subject, Exam update) {
        if (!existsBySubject(subject)) {
            throw new IllegalArgumentException("Exam with subject: " + subject + " does not exist");
        }
        Exam finalUpdate = examRepository.findBySubject(subject);
        if (update.getTitle() != null) finalUpdate.setTitle(update.getTitle());
        if (update.getResult() != null) finalUpdate.setResult(update.getResult());
        if (update.getStatus() != null) finalUpdate.setStatus(update.getStatus());
        return examRepository.save(finalUpdate);
    }

    public boolean delete(long id) {
        return examRepository.findAll().removeIf(exam -> exam.getId() == id);
    }

    public boolean deleteBySubject(String subject) {
        return examRepository.findAll().removeIf(exam -> exam.getSubject().equals(subject));
    }

}