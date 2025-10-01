package com.example.revive_app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
  @Id @GeneratedValue private Long id;

  @Column(nullable = false, unique = true) // enforce uniqueness
  private String name;

  private String description;

  @OneToOne
  @JoinColumn(name = "head_employee_id", nullable = true)
  private Employee head; // The head of the department (also an Employee)

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Employee> employees = new ArrayList();
}
