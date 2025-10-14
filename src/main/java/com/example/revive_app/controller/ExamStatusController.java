package com.example.revive_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.model.ExamStatus;
import com.example.revive_app.service.ExamStatusService;

@RestController
@RequestMapping("/api/exam-statuses")
public class ExamStatusController {
    
    private final ExamStatusService examStatusService;

    public ExamStatusController(ExamStatusService examStatusService) {
        this.examStatusService = examStatusService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ExamStatus>>> getAll() {
        ResponseDTO<List<ExamStatus>> response = new ResponseDTO<>();
        List<ExamStatus> examsStatus = examStatusService.findAll();
        if (examsStatus.isEmpty()) {
            response.setMessage("No exams status found.");
            response.setStatus(HttpStatus.NO_CONTENT.value());
            response.setSuccess(false);
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setMessage("Exams status retrieved successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        response.setData(null);
        response.setData(examsStatus);
        return ResponseEntity.ok(response);
    }
}
