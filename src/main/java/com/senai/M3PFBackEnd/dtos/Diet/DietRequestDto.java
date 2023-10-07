package com.senai.M3PFBackEnd.dtos.Diet;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record DietRequestDto(
        @NotBlank(message = "O campo Nome da Dieta é obrigatório!")
        @Size(min = 5, max = 100, message = "O campo Nome da Dieta deve ter no mínimo 5 e máximo 100 caracteres, respectivamente!")
        String dietName,

        @NotNull(message = "O campo Data da Dieta é obrigatório!")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dietDate,

        @NotNull(message = "O campo Horário da Dieta é obrigatório!")
        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
        LocalTime dietTime,

        @NotBlank(message = "O campo Tipo de Dieta é obrigatório!")
        @Pattern(
                regexp = "LOW_CARB|DASH|PALEOLITHIC|KETOGENIC|DUKAN|MEDITERRANEAN|OTHER",
                message = "O tipo de ser: LOW CARB, DASH, PALEOLITHIC, KETOGENIC, DUKAN, MEDITERRANEAN ou OTHER"
        )
        String type,

        //Atualizar e Vincular com entidade Pacientes
        @Column(nullable=false)
        String description,

        Boolean status
) {
}
