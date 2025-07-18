package com.example.revive_app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class DatabaseConfig {

  @Autowired
  DBCredentials config;

  @Bean
  @Primary
  public DataSource dataSource() {
    return DataSourceBuilder
        .create()
        .username(config.getUsername())
        .url(config.getUrl())
        .password(config.getPassword())
        .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
        .build();
  }
}