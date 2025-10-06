/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.model.Answer;
import com.example.revive_app.model.AnswerHistory;
import com.example.revive_app.model.ExamAttempt;
import com.example.revive_app.model.Question;
import com.example.revive_app.repository.AnswerHistoryRepository;
import com.example.revive_app.repository.AnswerRepository;
import com.example.revive_app.repository.ExamAttemptRepository;
import com.example.revive_app.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final ExamAttemptRepository examAttemptRepository;
    private final QuestionRepository questionRepository;
    private final AnswerHistoryRepository answerHistoryRepository;

    @Transactional
    public Answer saveOrUpdateAnswer(Long attemptId, Long questionId, Integer selectedOptionIndex) {
        ExamAttempt attempt = examAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Exam attempt not found"));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // Find existing answer
        Answer answer = answerRepository.findByExamAttemptIdAndQuestionId(attemptId, questionId).orElseGet(() -> {
            Answer newAnswer = new Answer();
            newAnswer.setExamAttempt(attempt);
            newAnswer.setQuestion(question);
            return newAnswer;
        });

        // 🧾 Optional: Record history if the user is changing their previous answer
        if (answer.getId() != null && answer.getSelectedOptionIndex() != null
                && !answer.getSelectedOptionIndex().equals(selectedOptionIndex)) {

            AnswerHistory history = new AnswerHistory();
            history.setExamAttemptId(attemptId);
            history.setQuestionId(questionId);
            history.setPreviousOptionIndex(answer.getSelectedOptionIndex());
            history.setChangedAt(LocalDateTime.now());
            answerHistoryRepository.save(history);
        }

        // Update the selected option
        answer.setSelectedOptionIndex(selectedOptionIndex);
        answer.setIsFinal(false); // not yet submitted
        answer.setIsCorrect(null);

        return answerRepository.save(answer);
    }

    public List<Answer> getAnswersByAttempt(Long attemptId) {
        return answerRepository.findByExamAttemptId(attemptId);
    }
}
