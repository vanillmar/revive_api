/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.service.StudentService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/stats/total")
    public ResponseEntity<ResponseDTO<Map<String, Long>>> getTotalStudents() {
        ResponseDTO<Map<String, Long>> response = new ResponseDTO<>();
        Long totalStudents = studentService.getTotalStudents();
        response.setData(Map.of("total", totalStudents));
        response.setSuccess(true);
        response.setMessage("Total students retrived successfully.");
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats/total-active")
    public ResponseEntity<ResponseDTO<Map<String, Long>>> getTotalActiveStudents() {
        ResponseDTO<Map<String, Long>> response = new ResponseDTO<>();
        Long totalActiveStudents = studentService.getTotalActiveStudents();
        response.setData(Map.of("total", totalActiveStudents));
        response.setSuccess(true);
        response.setMessage("Total active students retrived successfully.");
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats/total-inactive")
    public ResponseEntity<ResponseDTO<Map<String, Long>>> getTotalInactiveStudents() {
        ResponseDTO<Map<String, Long>> response = new ResponseDTO<>();
        Long totalInactiveStudents = studentService.getTotalInactiveStudents();
        response.setData(Map.of("total", totalInactiveStudents));
        response.setSuccess(true);
        response.setMessage("Total inactive students retrived successfully.");
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

}
