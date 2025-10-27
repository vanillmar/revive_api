/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.config;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // Replace this with your actual authentication logic
        return () -> Optional.of("system"); // e.g. SecurityContextHolder.getContext().getAuthentication().getName()
    }
}