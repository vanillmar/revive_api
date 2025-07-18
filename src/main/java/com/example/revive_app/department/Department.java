package com.example.revive_app.department;

import java.util.List;

import com.example.revive_app.employee.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToOne(optional = true)
    @JoinColumn(name = "responsible_employee_id", nullable = true)
    private Employee responsible;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public Department() {}

    public Department(String name, String description, Employee responsible) {
        this.name = name;
        this.description = description;
        this.responsible = responsible;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Employee getResponsible() { return responsible; }
    public void setResponsible(Employee responsible) { this.responsible = responsible; }
    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }
}
