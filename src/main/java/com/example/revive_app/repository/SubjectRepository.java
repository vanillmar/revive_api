package com.example.revive_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.revive_app.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}