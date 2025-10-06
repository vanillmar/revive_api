/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String question;

    @ElementCollection
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    @OrderColumn(name = "option_index")
    private List<String> options = new ArrayList<>();

    private int answerIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    @JsonBackReference
    private Subject subject;

    public Question(Long id, String question, List<String> options, int answerIndex, Subject subject) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.answerIndex = answerIndex;
        this.subject = subject;
    }
}
