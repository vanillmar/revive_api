/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Student")
@Getter
@Setter
@NoArgsConstructor
public class Student extends User {

    @Column(unique = true, nullable = false)
    private String studentId;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<ExamAttempt> examAttempts;

    @Column(unique = true)
    private String licenseNumber;
    private LocalDate licenseExpiryDate;

    @Column(unique = true)
    private String medicalCertificateNumber;
    private LocalDate medicalCertificateExpiryDate;

    private String qualification; // e.g. "Private Pilot License (PPL)", "Flight Instructor", etc.
    private String aircraftTypeRating; // e.g., "Cessna 172", "Boeing 737", etc.
    
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus enrollmentStatus; // e.g., ACTIVE, GRADUATED, SUSPENDED
}
