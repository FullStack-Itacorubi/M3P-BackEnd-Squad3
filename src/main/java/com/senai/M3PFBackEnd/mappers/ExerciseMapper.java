package com.senai.M3PFBackEnd.mappers;

import com.senai.M3PFBackEnd.dtos.exercises.ExerciseRequestPostDto;
import com.senai.M3PFBackEnd.dtos.exercises.ExerciseRequestPutDto;
import com.senai.M3PFBackEnd.entities.ExerciseEntity;
import com.senai.M3PFBackEnd.enums.exercise.ExerciseType;

public class ExerciseMapper {
    
    public static ExerciseEntity map(ExerciseRequestPostDto source) {
        ExerciseEntity target = new ExerciseEntity();

        target.setName(source.name());
        target.setDate(source.date());
        target.setTime(source.time());
        target.setType(ExerciseType.valueOf(source.type()));
        target.setWeeklyAmount(source.weeklyAmount());
        target.setDescription(source.description());

        return target;
    }

    public static ExerciseEntity map(ExerciseRequestPutDto source) {
        ExerciseEntity target = new ExerciseEntity();

        target.setName(source.name());
        target.setDate(source.date());
        target.setTime(source.time());
        target.setType(ExerciseType.valueOf(source.type()));
        target.setWeeklyAmount(source.weeklyAmount());
        target.setDescription(source.description());
        target.setStatus(source.status());

        return target;
    }
}
