package com.senai.M3PFBackEnd.dtos.exercises;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ExerciseRequestPutDto(
    @NotBlank(message = "O campo nome da série de exercícios é obrigatório!")
    @Size(min = 5, max = 100)
    String name,

    @NotBlank(message = "O campo tipo é obrigatório!")
    @Pattern(
        regexp = "AEROBICS|MUSCULAR|FLEXIBILITY|STRENGTH|AGILITY|OTHER",
        message = "O tipo deve ser: AEROBICS, MUSCULAR, FLEXIBILITY, STRENGTH, AGILITY ou OTHER")
    String type,

    @NotNull(message = "O campo quantidade por semana é obrigatório!")
    Integer weeklyAmount,

    @NotBlank(message = "O campo descrição é obrigatório!")
    @Size(min = 10, max = 1000)
    String description,
    
    @NotNull(message = "O campo status é obrigatório!")
    boolean status
    ) {
}
