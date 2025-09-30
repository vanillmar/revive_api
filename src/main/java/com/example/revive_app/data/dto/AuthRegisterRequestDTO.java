package com.example.revive_app.data.dto;    
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthRegisterRequestDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private int roleId;
    private boolean isActive;
}   