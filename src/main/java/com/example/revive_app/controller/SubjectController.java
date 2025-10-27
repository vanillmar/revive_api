/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.data.dto.subject.SubjectRequestDTO;
import com.example.revive_app.data.dto.subject.SubjectResponseDTO;
import com.example.revive_app.data.mapper.SubjectMapper;
import com.example.revive_app.model.Subject;
import com.example.revive_app.service.SubjectService;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    private final SubjectMapper subjectMapper;

    public SubjectController(SubjectService subjectService, SubjectMapper subjectMapper) {
        this.subjectService = subjectService;
        this.subjectMapper = subjectMapper;
    }

    @GetMapping("/stats/total")
    public ResponseEntity<ResponseDTO<Map<String, Long>>> getTotalSubjects() {
        ResponseDTO<Map<String, Long>> response = new ResponseDTO<>();
        Long totalSubjects = subjectService.getTotalSubjects();
        response.setData(Map.of("total", totalSubjects));
        response.setMessage("Total subjects loaded sucessfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<SubjectResponseDTO>>> getAllSubjects() {

        List<Subject> subjects = subjectService.findAll();
        List<SubjectResponseDTO> subjectDTOs = subjectMapper.toResponseList(subjects);
        ResponseDTO<List<SubjectResponseDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Subjects loaded sucessfully.");
        responseDTO.setData(subjectDTOs);
        responseDTO.setStatus(HttpStatus.OK.value());
        responseDTO.setSuccess(true);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<SubjectResponseDTO>> getSubjectById(@PathVariable Long id) {
        if (!subjectService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Subject subject = subjectService.findById(id);
        SubjectResponseDTO subjectResponseDTO = subjectMapper.toResponse(subject);

        ResponseDTO<SubjectResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Subject loaded sucessfully.");
        responseDTO.setData(subjectResponseDTO);
        responseDTO.setStatus(HttpStatus.OK.value());
        responseDTO.setSuccess(true);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<SubjectResponseDTO>> createSubject(@RequestBody SubjectRequestDTO request) {
        Subject subject = subjectMapper.toEntity(request);

        Subject savedSubject = subjectService.save(subject);
        SubjectResponseDTO subjectResponseDTO = subjectMapper.toResponse(savedSubject);

        ResponseDTO<SubjectResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Subject with id: " + subjectResponseDTO.getId() + " saved sucessfully.");
        responseDTO.setData(subjectResponseDTO);
        responseDTO.setStatus(HttpStatus.OK.value());
        responseDTO.setSuccess(true);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<SubjectResponseDTO>> updateSubject(@PathVariable Long id,
            @RequestBody SubjectRequestDTO request) {
        if (!subjectService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Subject subject = subjectService.findById(id);
        subject.setName(request.getName());
        subject.setDescription(request.getDescription());
        subject.setCode(request.getCode());

        Subject updatedSubject = subjectService.save(subject);
        SubjectResponseDTO subjectResponseDTO = subjectMapper.toResponse(updatedSubject);

        ResponseDTO<SubjectResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Subject with id: " + subjectResponseDTO.getId() + " updated sucessfully.");
        responseDTO.setData(subjectResponseDTO);
        responseDTO.setStatus(HttpStatus.OK.value());
        responseDTO.setSuccess(true);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        if (!subjectService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        subjectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}