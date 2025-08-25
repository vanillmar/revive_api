package com.example.revive_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Employee")
@DiscriminatorValue("EMPLOYEE")
@Getter
@Setter
public class Employee extends User {
  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "job_title")
  private String jobTitle;

  private Double salary;

  // Many employees can belong to one department
  @ManyToOne
  @JoinColumn(name = "department_id", nullable = true)
  private Department department;

  public Employee() {
    super();
  }

  public Employee(
      String username, String password, String email, String firstname, String lastname) {
    super(username, password, email);
    this.firstname = firstname;
    this.lastname = lastname;
  }
}
