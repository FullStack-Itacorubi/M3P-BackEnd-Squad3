package com.senai.M3PFBackEnd.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.senai.M3PFBackEnd.dtos.exercises.ExerciseRequestPostDto;
import com.senai.M3PFBackEnd.dtos.exercises.ExerciseRequestPutDto;
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
        return new ExerciseResponseDto(exerciseRepository.save(exercise));
    }

    public ExerciseResponseDto update(Long id, ExerciseRequestPutDto requestDto) {
        verifyIfHasId(id);
        ExerciseEntity exercise = ExerciseMapper.map(requestDto);
        exercise.setId(id);
        return new ExerciseResponseDto(exerciseRepository.save(exercise));
    }

    public List<ExerciseResponseDto> getExercises(String name) {
        // TODO adicionar retorno por nome de pacientes
        // if(!name.isBlank())

        return exerciseRepository.findAll().stream().map(ExerciseResponseDto::new).toList();
    }

    public ExerciseResponseDto getExerciseById(Long id) {
        verifyIfHasId(id);
        return new ExerciseResponseDto(exerciseRepository.getReferenceById(id));
    }

    public void delete(Long id) {
        verifyIfHasId(id);
        exerciseRepository.deleteById(id);
    }

    private void verifyIfHasId(Long id) {
        boolean isIdExists = exerciseRepository.existsById(id);

        if (!isIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id informado é inválido!"
            );
        }
    }
}
