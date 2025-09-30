package com.example.revive_app.data.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class AuthTokenValidResponseDTO {
  private boolean isValid;
}