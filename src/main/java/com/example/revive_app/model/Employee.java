package com.example.revive_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
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

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
}