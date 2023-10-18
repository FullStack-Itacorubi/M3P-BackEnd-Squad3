package com.senai.M3PFBackEnd.enums.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExerciseType {
    AEROBICS("Resistência Aeróbica"),
    MUSCULAR("Resistência Muscular"),
    FLEXIBILITY("Flexibilidade"),
    STRENGTH("Força"),
    AGILITY("Agilidade"),
    OTHER("Outro");

    private final String type;
}
