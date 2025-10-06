/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import com.example.revive_app.model.Answer;
import com.example.revive_app.repository.AnswerRepository;
import com.example.revive_app.service.AnswerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerRepository answerRepository;

    @PostMapping("/save")
    public Answer saveAnswer(@RequestParam Long attemptId, @RequestParam Long questionId,
            @RequestParam Integer selectedOptionIndex) {

        return answerService.saveOrUpdateAnswer(attemptId, questionId, selectedOptionIndex);
    }

    @GetMapping("/{attemptId}")
    public List<Answer> getSavedAnswers(@PathVariable Long attemptId) {
        return answerRepository.findByExamAttemptId(attemptId);
    }
}
