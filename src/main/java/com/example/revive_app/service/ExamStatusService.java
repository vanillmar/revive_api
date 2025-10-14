package com.example.revive_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.revive_app.model.ExamStatus;
import com.example.revive_app.repository.ExamStatusRepository;

@Service
public class ExamStatusService {
    
    private final ExamStatusRepository examStatusRepository;

    public ExamStatusService(ExamStatusRepository examStatusRepository) {
        this.examStatusRepository = examStatusRepository;
    }

    public List<ExamStatus> findAll() {
        return examStatusRepository.findAll();
    }

}
