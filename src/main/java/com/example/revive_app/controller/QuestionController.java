package com.example.revive_app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.example.revive_app.model.Question;
import com.example.revive_app.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable String id) {
        Optional<Question> questions = questionService.findById(id);
        if (questions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(questions);
    }

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.save(question);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable String id, @RequestBody Question questionDetails) {
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
    //     Optional<Question> question = questionService.findById(id);
    //     if (question.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     questionService.deleteById(id);
    //     return ResponseEntity.noContent().build();
    // }
}