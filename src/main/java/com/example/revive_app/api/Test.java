package com.example.revive_app.api;

import com.example.revive_app.config.DbCredentials;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@EnableConfigurationProperties(DbCredentials.class)
public class Test {
  private final DbCredentials dbCredentials;

  public Test(DbCredentials dbCredentials) {
    // Constructor logic if needed
    this.dbCredentials = dbCredentials;
  }

  @GetMapping
  public String getTest() {
    return "Test endpoint is working!";
  }

  @GetMapping("/credentials")
  public String getCredentials() {
    return String.format(
        "Database URL: %s, Database Username: %s, and Database Password: %s",
        dbCredentials.getUrl(), dbCredentials.getUsername(), dbCredentials.getPassword());
  }
}
