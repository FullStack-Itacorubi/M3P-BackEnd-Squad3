package com.senai.M3PFBackEnd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.senai.M3PFBackEnd.dtos.exercises.ExerciseRequestPostDto;
import com.senai.M3PFBackEnd.dtos.exercises.ExerciseResponseDto;
import com.senai.M3PFBackEnd.services.ExerciseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/exercicios")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<ExerciseResponseDto> registerExercise(
        @RequestBody @Valid ExerciseRequestPostDto requestDto) {
        return null;
    }
}
