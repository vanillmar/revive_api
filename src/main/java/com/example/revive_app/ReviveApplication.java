package com.example.revive_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.revive_app.config.DbCredentials;

@SpringBootApplication
@EnableConfigurationProperties(DbCredentials.class)
public class ReviveApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ReviveApplication.class);
    private final DbCredentials dbCredentials;

    public ReviveApplication(DbCredentials dbCredentials) {
        this.dbCredentials = dbCredentials;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReviveApplication.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("Application started successfully!");
        if (dbCredentials != null) {
            logger.info("Database URL: {}", dbCredentials.getUrl());
            logger.info("Database Username: {}", dbCredentials.getUsername());
        } else {
            logger.warn("Database credentials are not configured!");
        }
    }
}