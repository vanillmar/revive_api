/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Question")
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
    @CollectionTable(name = "QuestionOptions", joinColumns = @JoinColumn(name = "question_id"))
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
