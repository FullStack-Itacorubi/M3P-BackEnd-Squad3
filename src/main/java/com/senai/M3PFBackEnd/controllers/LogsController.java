package com.senai.M3PFBackEnd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.M3PFBackEnd.dtos.logs.LogResponseDto;
import com.senai.M3PFBackEnd.services.LogsService;

@RestController
@RequestMapping("/logs")
public class LogsController {

    @Autowired
    private LogsService logsService;

    @GetMapping
    public ResponseEntity<List<LogResponseDto>> getLogs() {
        return ResponseEntity.ok(logsService.getLogs());
    }
}
