package com.senai.M3PFBackEnd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.senai.M3PFBackEnd.dtos.exercises.ExerciseRequestPostDto;
import com.senai.M3PFBackEnd.dtos.exercises.ExerciseRequestPutDto;
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
        ExerciseResponseDto responseDto = exerciseService.save(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ExerciseResponseDto> updateExercise(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid ExerciseRequestPutDto requestDto) {
        ExerciseResponseDto responseDto = exerciseService.update(id, requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
