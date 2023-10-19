package com.senai.M3PFBackEnd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.M3PFBackEnd.dtos.stats.GenericStatDTO;
import com.senai.M3PFBackEnd.services.StatsService;

@RestController
@RequestMapping("/estatisticas")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping
    public ResponseEntity<List<GenericStatDTO>> getStatistics() {
        List<GenericStatDTO> response = statsService.getStatistics();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
