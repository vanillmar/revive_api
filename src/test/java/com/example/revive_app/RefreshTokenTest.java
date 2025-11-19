package com.example.revive_app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
 class RefreshTokenTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRefreshTokenFlow() throws Exception {
        // Step 1: Login to get initial access token and refresh token
        String loginRequest = """
            {
                "username": "testOne",
                "password": "admin123"
            }
            """;

        MvcResult loginResult = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.token").exists())
                .andExpect(jsonPath("$.data.refreshToken").exists())
                .andReturn();

        // Extract tokens from login response
        String loginResponse = loginResult.getResponse().getContentAsString();
        JsonNode loginJson = objectMapper.readTree(loginResponse);
        String token = loginJson.get("data").get("token").asText();
        String refreshToken = loginJson.get("data").get("refreshToken").asText();

        assertNotNull(token, "Access token should not be null");
        assertNotNull(refreshToken, "Refresh token should not be null");

        // Step 2: Use refresh token to get new access token
        String refreshRequest = """
            {
                "refreshToken": "%s"
            }
            """.formatted(refreshToken);

        MvcResult refreshResult = mockMvc.perform(post("/api/auth/refresh-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(refreshRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.token").exists())
                .andReturn();

        // Extract new access token
        String refreshResponse = refreshResult.getResponse().getContentAsString();
        JsonNode refreshJson = objectMapper.readTree(refreshResponse);
        String newToken = refreshJson.get("data").get("token").asText();

        assertNotNull(newToken, "New access token should not be null");
        assertNotEquals(token, newToken, "New access token should be different from old one");

        // Step 3: Verify new access token works
        mockMvc.perform(get("/api/users/me")
                .header("Authorization", "Bearer " + newToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testRefreshTokenWithInvalidToken() throws Exception {
        String invalidRefreshRequest = """
            {
                "refreshToken": "invalid_token_here"
            }
            """;

        mockMvc.perform(post("/api/auth/refresh-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRefreshRequest))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testRefreshTokenWithExpiredToken() throws Exception {
        // This assumes you have a way to generate an expired token
        String expiredToken = "expired_refresh_token_here";
        
        String refreshRequest = """
            {
                "refreshToken": "%s"
            }
            """.formatted(expiredToken);

        mockMvc.perform(post("/api/auth/refresh-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(refreshRequest))
                .andExpect(status().isUnauthorized());
    }
}