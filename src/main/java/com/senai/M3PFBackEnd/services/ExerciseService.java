package com.senai.M3PFBackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.senai.M3PFBackEnd.dtos.exercises.ExerciseRequestPostDto;
import com.senai.M3PFBackEnd.dtos.exercises.ExerciseResponseDto;
import com.senai.M3PFBackEnd.entities.ExerciseEntity;
import com.senai.M3PFBackEnd.mappers.ExerciseMapper;
import com.senai.M3PFBackEnd.repositories.ExerciseRepository;

@Service
public class ExerciseService {
    
    @Autowired
    ExerciseRepository exerciseRepository;

    public ExerciseResponseDto save(ExerciseRequestPostDto requestDto) {
        ExerciseEntity exercise = ExerciseMapper.map(requestDto);
        return new ExerciseResponseDto(exercise);
    }
}
