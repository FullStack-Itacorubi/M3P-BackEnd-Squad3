package com.senai.M3PFBackEnd.dtos.medicament;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record MedicamentRequestPutDto(
        @NotBlank(message = "O campo nome é obrigatório!")
        @Size(
                min = 5,
                max = 100,
                message = "O campo nome deve ter entre 5 a 100 caracteres."
        )
        String name,

        @NotBlank(message = "O campo tipo do medicamento é obrigatório!")
        @Pattern(
                regexp = "CAPSULE|PILL|LIQUID|CREAM|GEL|INHALATION|INJECTION|SPRAY",
                message = "O campo tipo de medicamento é: CAPSULE, PILL, LIQUID, CREAM, GEL, INHALATION, INJECTION ou SPRAY"
        )
        String type,

        @NotNull(message = "O campo quantidade do medicamento é obrigatório!")
        /*@Pattern(
                regexp = "^[0-9]+(\\.[0-9]{1,2})?$",
                message = "O campo quantidade do medicamento deve ser no formato: 1,234.56"
        )*/
        @DecimalMin(
                value = "0.0",
                inclusive = false,
                message = "O campo quantidade do medicamento deve ter valor maior ou igual a 0.0"
        )
        @Digits(
                integer = 5,
                fraction = 2,
                message = "O campo quantidade do medicamento deve ter duas casas decimais. "
        )
        Double quantity,

        @NotBlank(message = "O campo unidade da quantidade do medicamento é obrigatório!")
        @Pattern(
                regexp = "MG|MCG|G|ML|PERCENTAGE",
                message = "O campo unidade da quantidade de medicamento é: MG, MCG, G, ML ou PERCENTAGE"
        )
        String unit,

        @NotBlank(message = "O campo observações é obrigatório!")
        @Size(
                min = 10,
                max = 1000,
                message = "O campo observações deve ter entre 10 a 1000 caracteres."
        )
        String observations,

        @NotNull(message = "O campo status é obrigatório!")
        Boolean status
) {
}
