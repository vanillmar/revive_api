package com.example.revive_app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AnswerHistory")
@Getter
@Setter
@NoArgsConstructor
public class AnswerHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long examAttemptId;
  private Long questionId;
  private Integer previousOptionIndex;
  private LocalDateTime changedAt;
}
