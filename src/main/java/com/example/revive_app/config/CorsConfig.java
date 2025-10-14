/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000") // Your frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*", "Access-Control-Allow-Origin', 'http://localhost:3000") // Allow all headers
                .allowedHeaders("*", "Access-Control-Allow-Origin', 'http://127.0.0.1:3000") // Allow all headers

                .allowCredentials(true); // If you need cookies/sessions
    }
}
