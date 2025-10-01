package com.example.revive_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
public class Subject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  private String code;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "status_id")
  private ExamStatus status;

  @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
  @JsonBackReference
  private List<Question> questions = new ArrayList<>();

  public Subject(Long id, String name, String description, String code, ExamStatus status) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.code = code;
    this.status = status;
  }
}
