package com.senai.M3PFBackEnd.dtos.logs;

import java.text.SimpleDateFormat;

import com.senai.M3PFBackEnd.entities.LogMessage;

public record LogResponseDto(String message) {
    public LogResponseDto(LogMessage logMessage) {
        this(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(logMessage.getTimestamp()) +
                ": " + logMessage.getMessage());
    }
}
