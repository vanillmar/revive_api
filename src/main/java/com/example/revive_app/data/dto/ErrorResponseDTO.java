package com.example.revive_app.data.dto;

import java.time.Instant;

public interface ErrorResponseDTO {
    public Instant getTimestamp();
    public int getStatus();
    public String getError();
    public String getMessage();
    public String getPath();
}
