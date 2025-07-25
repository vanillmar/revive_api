package com.example.revive_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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

    public Employee() {
        super();
    }
    
    public Employee(String username, String password, String email, String firstname, String lastname) {
        super(username, password, email);
        this.firstname = firstname;
        this.lastname = lastname;
    }
}