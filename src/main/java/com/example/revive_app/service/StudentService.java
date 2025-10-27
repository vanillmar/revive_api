/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.model.Student;
import com.example.revive_app.repository.StudentRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Long getTotalStudents() {
        return studentRepository.count();
    }

    public Long getTotalActiveStudents() {
        Student probe = new Student();
        probe.setActive(true);

        Example<Student> example = Example.of(probe);

        return studentRepository.count(example);
    }

    public Long getTotalInactiveStudents() {
        Student probe = new Student();
        probe.setActive(false);

        Example<Student> example = Example.of(probe);

        return studentRepository.count(example);
    }

}
