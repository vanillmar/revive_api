package com.example.revive_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DBCredentials.class)
public class ReviveApplication implements CommandLineRunner {
	private final DBCredentials credentials;
	public ReviveApplication(DBCredentials credentials) {
		this.credentials = credentials;
	}	

	public static void main(String[] args) {
		SpringApplication.run(ReviveApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Logger logger = LoggerFactory.getLogger(ReviveApplication.class);
		logger.info("----------------------------------------");
		logger.info("Configuration properties");
		logger.info("   db.username is {}", credentials.getUsername());
		logger.info("   db.password is {}", credentials.getPassword());
		logger.info("----------------------------------------");
	}
}
