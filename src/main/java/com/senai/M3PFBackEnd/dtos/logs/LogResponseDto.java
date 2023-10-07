package com.senai.M3PFBackEnd.dtos.logs;

import java.time.LocalDateTime;
import com.senai.M3PFBackEnd.entities.LogMessage;

public record LogResponseDto(String message, LocalDateTime timestamp) {
    public LogResponseDto(LogMessage logMessage) {
        this(logMessage.getMessage(), logMessage.getTimestamp());
    }
}
