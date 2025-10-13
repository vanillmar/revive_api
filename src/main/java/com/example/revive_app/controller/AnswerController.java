/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import com.example.revive_app.model.Answer;
import com.example.revive_app.repository.AnswerRepository;
import com.example.revive_app.service.AnswerService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.data.dto.answer.AnswerResponseDTO;
import com.example.revive_app.data.mapper.AnswerMapper;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    public AnswerController(AnswerService answerService, AnswerRepository answerRepository, AnswerMapper answerMapper) {
        this.answerService = answerService;
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
    }
    
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<AnswerResponseDTO>> saveAnswer(@RequestParam Long attemptId, @RequestParam Long questionId,
            @RequestParam Integer selectedOptionIndex) {
        Answer answer = answerService.saveOrUpdateAnswer(attemptId, questionId, selectedOptionIndex);
        AnswerResponseDTO answerResponseDTO = answerMapper.toResponse(answer);
        ResponseDTO<AnswerResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Answer saved sucesfully.");
        responseDTO.setData(answerResponseDTO);
        responseDTO.setSuccess(true);
        responseDTO.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{attemptId}")
    public ResponseEntity<ResponseDTO<List<AnswerResponseDTO>>> getSavedAnswers(@PathVariable Long attemptId) {
        List<Answer> answers = answerRepository.findByExamAttemptId(attemptId);
        List<AnswerResponseDTO> answersResponse = answerMapper.toResponseList(answers);
        ResponseDTO<List<AnswerResponseDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Saved answers fetched sucessfully.");
        responseDTO.setStatus(HttpStatus.OK.value());
        responseDTO.setSuccess(true);
        responseDTO.setData(answersResponse);
        return ResponseEntity.ok(responseDTO);
    }
}
