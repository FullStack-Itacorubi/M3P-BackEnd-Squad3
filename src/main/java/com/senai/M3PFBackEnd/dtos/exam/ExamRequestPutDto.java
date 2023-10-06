package com.senai.M3PFBackEnd.dtos.exam;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record ExamRequestPutDto(
        @NotBlank(message = "O campo nome do exame é obrigatório!")
        @Size(min = 8, max = 64, message = "O campo nome do exame deve ter de 6 a 64 caracteres!")
        String examName,

        @NotNull(message = "O campo data do exame é obrigatório!")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate examDate,

        @NotNull(message = "O campo hora do exame é obrigatório!")
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime examHour,

        @NotBlank(message = "O campo tipo do exame é obrigatório!")
        @Size(min = 4, max = 32, message = "O campo nome do exame deve ter de 4 a 32 caracteres!")
        String examType,

        @NotBlank(message = "O campo laboratório é obrigatório!")
        @Size(min = 4, max = 32, message = "O campo nome do exame deve ter de 4 a 32 caracteres!")
        String laboratory,

        String documentUrl,

        @NotBlank(message = "O campo resultados é obrigatório!")
        @Size(min = 16, max = 1024, message = "O campo nome do exame deve ter de 16 a 1024 caracteres!")
        String results,

        Boolean status
) {
}
