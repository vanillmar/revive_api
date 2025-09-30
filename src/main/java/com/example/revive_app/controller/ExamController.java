package com.example.revive_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.revive_app.data.dto.ExamRequestDTO;
import com.example.revive_app.data.dto.ExamResponseDTO;
import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.data.mapper.ExamMapper;
import com.example.revive_app.model.Exam;
import com.example.revive_app.service.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;
    private final ExamMapper examMapper;

    @Autowired
    public ExamController(ExamService examService, ExamMapper examMapper) {
        this.examService = examService;
        this.examMapper = examMapper;
    }

    // @PreAuthorize("hasAuthority('" + Permissions.READ_EXAMS + "')")
    @GetMapping
    public ResponseEntity<ResponseDTO<List<ExamResponseDTO>>> list() {
        ResponseDTO<List<ExamResponseDTO>> response = new ResponseDTO<>();
        List<Exam> exams = examService.findAll();
        if(exams.isEmpty()){ 
            response.setMessage("No exams found");
            response.setStatus(HttpStatus.NO_CONTENT.value());
            response.setSuccess(false);
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        
        List<ExamResponseDTO> examDTOs = examMapper.toResponseDTOs(exams);
        response.setMessage("No exams found");
        response.setStatus(HttpStatus.NO_CONTENT.value());
        response.setSuccess(false);
        response.setData(null);
        response.setData(examDTOs);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/{subject}")
    public ResponseEntity<ResponseDTO<ExamResponseDTO>> getBySubject(@PathVariable String subject) {
        ResponseDTO<ExamResponseDTO> response = new ResponseDTO<>();
        ExamResponseDTO exam = examService.findBySubject(subject)
                .map(examMapper::toResponseDTO)
                .orElse(null);
        if (exam == null) {
            response.setMessage("Exam not found");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setMessage("Exam found");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        response.setData(exam);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<ExamResponseDTO>> create(@RequestBody ExamRequestDTO examDTO) {
        ResponseDTO<ExamResponseDTO> response = new ResponseDTO<>();
        Exam exam = examMapper.toEntity(examDTO);
        ExamResponseDTO data = examMapper.toResponseDTO(examService.create(exam));
        response.setMessage("Exam created successfully");
        response.setSuccess(true);
        response.setStatus(HttpStatus.CREATED.value());
        response.setData(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{subject}")
    public ResponseEntity<ResponseDTO<ExamResponseDTO>> update(@PathVariable String subject, @RequestBody ExamRequestDTO update) {
        ResponseDTO<ExamResponseDTO> response = new ResponseDTO<>();
        Exam exam = examMapper.toEntity(update);
        Exam UpdatedExam = examService.update(subject, exam);
        ExamResponseDTO data = examMapper.toResponseDTO(UpdatedExam);
        response.setMessage("Exam updated successfully");
        response.setSuccess(true);
        response.setStatus(HttpStatus.OK.value());
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{subject}")
    public ResponseEntity<?> delete(@PathVariable String subject) {
        if (examService.deleteBySubject(subject)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}