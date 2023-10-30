package com.senai.M3PFBackEnd.dtos.diet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record DietRequestPutDto(
        @NotBlank(message = "O campo Nome da Dieta é obrigatório!")
        @Size(min = 5, max = 100, message = "O campo Nome da Dieta deve ter no mínimo 5 e máximo 100 caracteres, respectivamente!")
        String dietName,

        @NotBlank(message = "O campo Tipo de Dieta é obrigatório!")
        @Pattern(
                regexp = "LOW_CARB|DASH|PALEOLITHIC|KETOGENIC|DUKAN|MEDITERRANEAN|OTHER",
                message = "O tipo deve ser: LOW CARB, DASH, PALEOLITHIC, KETOGENIC, DUKAN, MEDITERRANEAN ou OTHER"
        )
        String type,

        String description,

        @NotNull(message = "O campo status é obrigatório!")
        Boolean status
) {
}
