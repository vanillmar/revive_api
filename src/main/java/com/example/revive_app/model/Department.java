package com.example.revive_app.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true) // enforce uniqueness
    private String name;
    
    private String description; 

    @OneToOne
    @JoinColumn(name = "head_employee_id")
    private Employee head; // The head of the department (also an Employee)

    @OneToMany(mappedBy = "department")
    private List<Employee> employees; // All employees in this department
}
