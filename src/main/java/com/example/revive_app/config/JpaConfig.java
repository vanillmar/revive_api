package com.example.revive_app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@EnableConfigurationProperties(DbCredentials.class)
@Configuration
public class JpaConfig {

  private final DbCredentials dbCredentials;

  public JpaConfig(DbCredentials dbCredentials) {
    this.dbCredentials = dbCredentials;
  }

  @Bean
  public DataSource dataSource() {
    return DataSourceBuilder.create()
        .url(dbCredentials.getUrl())
        .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
        .username(dbCredentials.getUsername())
        .password(dbCredentials.getPassword())
        .build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource);
    em.setPackagesToScan("com.example.revive_app.model"); // Adjust to your package

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);

    Properties jpaProps = new Properties();
    jpaProps.setProperty("hibernate.hbm2ddl.auto", "create");
    jpaProps.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
    jpaProps.setProperty("hibernate.show_sql", "true");
    em.setJpaProperties(jpaProps);

    return em;
  }

  @Bean
  public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
    return new JpaTransactionManager(emf.getObject());
  }
}
