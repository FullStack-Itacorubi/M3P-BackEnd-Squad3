package com.senai.M3PFBackEnd.dtos.Query;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record QueryRequestDto(
        @NotBlank(message = "O campo Motivo da consulta é obrigatório!")
        @Size(min = 8, message = "O campo Motivo da consulta deve ter no mínimo 8 caracteres!")
        String reasonForConsultation,

        @NotNull(message = "O campo Data da Consulta é obrigatório!")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate consultationDate,

        @NotNull(message = "O campo Horário da Consulta é obrigatório!")
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime consultationTime,

        @NotBlank(message = "O campo Descrição do Problema é obrigatório!")
        @Size(min = 16, max = 1024, message = "O campo Descrição do Problema deve ter no mínimo 16 e máximo 1024 caracteres, respectivamente!")
        String problemDescription,

        //Atualizar e Vincular com entidade Medicamentos
        String prescriptionMedication,

        @NotBlank(message = "O campo Dosagem e Precauções é obrigatório!")
        @Size(min = 16, max = 256, message = "O campo Dosagem e Precauções deve ter no mínimo 16 e máximo 256 caracteres, respectivamente!")
        String dosageAndRecautions

) {
}
