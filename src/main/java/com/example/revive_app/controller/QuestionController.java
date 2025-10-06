/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.model.Question;
import com.example.revive_app.model.QuestionBank;
import com.example.revive_app.service.QuestionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<Question>>> getAllQuestions() {
        List<Question> questions = questionService.findAll();
        ResponseDTO<List<Question>> responseDTO = new ResponseDTO<>();
        if (questions.isEmpty()) {
            responseDTO.setMessage("No questions found");
            responseDTO.setStatus(HttpStatus.NO_CONTENT.value());
            responseDTO.setSuccess(false);
            responseDTO.setData(questions);
            return ResponseEntity.ok(responseDTO);
        }
        responseDTO.setMessage("Questions retrieved successfully");
        responseDTO.setStatus(HttpStatus.OK.value());
        responseDTO.setSuccess(true);
        responseDTO.setData(questions);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable Long id) {
        Optional<Question> questions = questionService.findById(id);
        if (questions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(questions);
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
    public Question createQuestion(@RequestBody Question question) {
        return questionService.save(question);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question questionDetails) {
        Optional<Question> questionOptional = questionService.findById(id);
        if (questionOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Question question = questionOptional.get();
        question.setQuestion(questionDetails.getQuestion());
        question.setOptions(questionDetails.getOptions());
        question.setAnswerIndex(questionDetails.getAnswerIndex());
        question.setSubject(questionDetails.getSubject());
        Question updatedQuestion = questionService.save(question);
        return ResponseEntity.ok(updatedQuestion);
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteQuestion(@PathVariable String id) {
    // Optional<Question> question = questionService.findById(id);
    // if (question.isEmpty()) {
    // return ResponseEntity.notFound().build();
    // }
    // questionService.deleteById(id);
    // return ResponseEntity.noContent().build();
    // }
}
