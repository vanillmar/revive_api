/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class ReviveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReviveApplication.class, args);
    }
}
