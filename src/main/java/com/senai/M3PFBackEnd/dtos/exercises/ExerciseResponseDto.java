package com.senai.M3PFBackEnd.dtos.exercises;

import java.time.LocalTime;
import java.time.LocalDate;
import com.senai.M3PFBackEnd.entities.ExerciseEntity;

public record ExerciseResponseDto(
    Long id,
    String name,
    LocalDate date,
    LocalTime time,
    String type,
    Integer weeklyAmount,
    String description,
    boolean status
) {

    public ExerciseResponseDto(ExerciseEntity exercise) {
        this(
            exercise.getId(),
            exercise.getName(),
            exercise.getDate(),
            exercise.getTime(),
            exercise.getType().getType(),
            exercise.getWeeklyAmount(),
            exercise.getDescription(),
            exercise.isStatus()
        );
    }
}
