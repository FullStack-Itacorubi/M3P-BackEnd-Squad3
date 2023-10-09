package com.senai.M3PFBackEnd.dtos.exercises;

import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ExerciseRequestPostDto(
                @NotBlank(message = "O campo nome da série de exercícios é obrigatório!")
                @Size(min = 5, max = 100)
                String name,

                @NotNull(message = "O campo data é obrigatório!")
                @JsonFormat(pattern = "dd/MM/yyyy")
                LocalDate date,

                @NotNull(message = "O campo horário é obrigatório!")
                @JsonFormat(pattern = "HH:mm:ss")
                LocalTime time,

                @NotBlank(message = "O campo tipo é obrigatório!")
                @Pattern(
                    regexp = "AEROBICS|MUSCULAR|FLEXIBILITY|STRENGTH|AGILITY|OTHER",
                    message = "O tipo deve ser: AEROBICS, MUSCULAR, FLEXIBILITY, STRENGTH, AGILITY ou OTHER")
                String type,

                @NotNull(message = "O campo quantidade por semana é obrigatório!")
                Integer weeklyAmount,

                @NotBlank(message = "O campo descrição é obrigatório!")
                @Size(min = 10, max = 1000)
                String description) {

}
