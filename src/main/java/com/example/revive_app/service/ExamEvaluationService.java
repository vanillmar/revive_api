package com.example.revive_app.service;

import com.example.revive_app.model.Answer;
import com.example.revive_app.model.ExamAttempt;
import com.example.revive_app.repository.AnswerRepository;
import com.example.revive_app.repository.ExamAttemptRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamEvaluationService {

  private final ExamAttemptRepository examAttemptRepository;
  private final AnswerRepository answerRepository;

  @Transactional
  public double evaluateExam(Long examAttemptId) {
    // 1️⃣ Fetch the exam attempt and all its answers
    ExamAttempt examAttempt =
        examAttemptRepository
            .findById(examAttemptId)
            .orElseThrow(() -> new RuntimeException("Exam attempt not found"));

    List<Answer> answers = answerRepository.findByExamAttemptId(examAttemptId);

    if (answers.isEmpty()) {
      throw new RuntimeException("No answers found for this exam attempt");
    }

    // 2️⃣ Count correct answers
    long correctCount = 0;
    for (Answer ans : answers) {
      boolean isCorrect =
          ans.getSelectedOptionIndex() != null
              && ans.getSelectedOptionIndex() == ans.getQuestion().getAnswerIndex();
      ans.setIsCorrect(isCorrect);

      if (isCorrect) {
        correctCount++;
      }
    }

    // 3️⃣ Calculate score (percentage)
    double score = (double) correctCount / answers.size() * 100;

    // 4️⃣ Save results
    examAttempt.setScore(score);
    examAttemptRepository.save(examAttempt);
    answerRepository.saveAll(answers);

    return score;
  }
}
