/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.data.dto.question.QuestionRequestDTO;
import com.example.revive_app.data.dto.question.QuestionResponseDTO;
import com.example.revive_app.data.mapper.QuestionMapper;
import com.example.revive_app.model.Question;
import com.example.revive_app.model.QuestionBank;
import com.example.revive_app.model.Subject;
import com.example.revive_app.service.QuestionService;
import com.example.revive_app.service.SubjectService;
import java.util.List;
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

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final SubjectService subjectService;

    public QuestionController(QuestionService questionService, QuestionMapper questionMapper,
            SubjectService subjectService) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<QuestionResponseDTO>>> getAllQuestions() {
        List<Question> questions = questionService.findAll();
        ResponseDTO<List<QuestionResponseDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(questionMapper.toResponseList(questions));
        if (questions.isEmpty()) {
            responseDTO.setMessage("No questions found");
            responseDTO.setStatus(HttpStatus.NO_CONTENT.value());
            responseDTO.setSuccess(false);
            return ResponseEntity.ok(responseDTO);
        }
        responseDTO.setMessage("Questions retrieved successfully");
        responseDTO.setStatus(HttpStatus.OK.value());
        responseDTO.setSuccess(true);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<QuestionResponseDTO>> getQuestionById(@PathVariable Long id) {
        Question question = questionService.findById(id);
        ResponseDTO<QuestionResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Questions retrieved successfully");
        responseDTO.setStatus(HttpStatus.OK.value());
        responseDTO.setSuccess(true);
        responseDTO.setData(questionMapper.toResponse(question));
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/subject/{subject}")
    public ResponseEntity<ResponseDTO<QuestionBank>> getQuestionBySubject(@PathVariable String subject) {
        List<Question> questions = questionService.findBySubjectName(subject);
        QuestionBank questionBank = new QuestionBank();
        ResponseDTO<QuestionBank> responseDTO = new ResponseDTO<>();
        if (questions.isEmpty()) {
            questionBank.setSubject(subject);
            questionBank.setQuestionCount(questions.size());
            questionBank.setQuestions(questions);
            responseDTO.setMessage("No questions found for subject: " + subject);
            responseDTO.setStatus(HttpStatus.NO_CONTENT.value());
            responseDTO.setSuccess(false);
            responseDTO.setData(questionBank);
            return ResponseEntity.ok(responseDTO);
        }
        questionBank.setSubject(subject);
        questionBank.setQuestionCount(questions.size());
        questionBank.setQuestions(questions);
        responseDTO.setMessage("Questions retrieved successfully for subject: " + subject);
        responseDTO.setStatus(HttpStatus.OK.value());
        responseDTO.setSuccess(true);
        responseDTO.setData(questionBank);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<QuestionResponseDTO>> createQuestion(
            @RequestBody QuestionRequestDTO questionRequest) {
        Subject subject = subjectService.findById(questionRequest.getSubjectId());
        Question question = questionMapper.toEntity(questionRequest, subject);
        Question savedQuestion = questionService.save(question);
        ResponseDTO<QuestionResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Question created successfully.");
        responseDTO.setStatus(HttpStatus.OK.value());
        responseDTO.setSuccess(true);
        responseDTO.setData(questionMapper.toResponse(savedQuestion));
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<QuestionResponseDTO>> updateQuestion(@PathVariable Long id,
            @RequestBody QuestionRequestDTO questionDetails) {
        ResponseDTO<QuestionResponseDTO> responseDTO = new ResponseDTO<>();
        Subject subject = subjectService.findById(questionDetails.getSubjectId());

        Question question = questionMapper.toEntity(questionDetails, subject);
        Question updatedQuestion = questionService.save(question);
        responseDTO.setMessage("Question created successfully.");
        responseDTO.setStatus(HttpStatus.OK.value());
        responseDTO.setSuccess(true);
        responseDTO.setData(questionMapper.toResponse(updatedQuestion));
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        if (!questionService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        questionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
