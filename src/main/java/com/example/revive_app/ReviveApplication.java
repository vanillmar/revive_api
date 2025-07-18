package com.example.revive_app;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(DBCredentials.class)
public class ReviveApplication implements CommandLineRunner {
	private final DBCredentials credentials;

	@Autowired
	public ReviveApplication(DBCredentials credentials) {
		this.credentials = credentials;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReviveApplication.class, args);
	}

	@Bean
	@RefreshScope
	public DataSource dataSource(DBCredentials credentials) {
		return DataSourceBuilder
			.create()
			.url(credentials.getUrl())
			.username(credentials.getUsername())
			.password(credentials.getPassword())
			.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
			.build();
	}

	@Override
	public void run(String... args) throws Exception {
		Logger logger = LoggerFactory.getLogger(ReviveApplication.class);
		logger.info("----------------------------------------");
		logger.info("Configuration properties");
		logger.info("   db.username is {}", credentials.getUsername());
		logger.info("   db.password is {}", credentials.getPassword());
		logger.info("   db.url is {}", credentials.getUrl());
		logger.info("----------------------------------------");
	}
}
