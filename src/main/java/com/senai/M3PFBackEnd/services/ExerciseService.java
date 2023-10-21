package com.senai.M3PFBackEnd.services;

import java.util.Collection;
import java.util.List;

import com.senai.M3PFBackEnd.repositories.PatientRepository;
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
import com.senai.M3PFBackEnd.repositories.MedicalRecordRepository;

@Service
public class ExerciseService {
    
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private PatientRepository patientRepository;

    public ExerciseResponseDto save(ExerciseRequestPostDto requestDto) {
        verifyPatientIdExists(requestDto.patientId());

        ExerciseEntity exercise = ExerciseMapper.map(requestDto);
        exercise = exerciseRepository.save(exercise);
        medicalRecordService.addExerciseToPatient(exercise, requestDto.patientId());

        return new ExerciseResponseDto(exercise);
    }

    public ExerciseResponseDto update(Long id, ExerciseRequestPutDto requestDto) {
        verifyIfHasId(id);
        ExerciseEntity exercise = ExerciseMapper.map(requestDto);
        exercise.setId(id);
        return new ExerciseResponseDto(exerciseRepository.save(exercise));
    }

    public List<ExerciseResponseDto> getExercises(String name) {
        if(name != null && !name.isBlank()) {
            List<ExerciseEntity> exercises = medicalRecordRepository
                .findAllByPatientFullNameContainingIgnoringCase(name).stream()
                .map(r -> r.getExercises()).flatMap(Collection::stream).toList();
            return exercises.stream().map(ExerciseResponseDto::new).toList();
        }

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

    private void verifyPatientIdExists(Long id) {
        boolean isUserIdExists = this.patientRepository.existsById(id);

        if (!isUserIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id do paciente é inválido!");
        }
    }
}
