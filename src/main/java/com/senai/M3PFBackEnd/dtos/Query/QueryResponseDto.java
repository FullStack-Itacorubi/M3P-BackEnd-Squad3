package com.senai.M3PFBackEnd.dtos.Query;

import com.senai.M3PFBackEnd.entities.QueryEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public record QueryResponseDto(
        Long id,
        String reasonForConsultation,
        LocalDate consultationDate,
        LocalTime consultationTime,
        String problemDescription,

        //Atualizar e Vincular com entidade Medicamentos
        String prescriptionMedication,
        String dosageAndRecautions,
        Boolean status
) {
    public QueryResponseDto(QueryEntity queryEntity) {
        this(
                queryEntity.getId(),
                queryEntity.getReasonForConsultation(),
                queryEntity.getConsultationDate(),
                queryEntity.getConsultationTime(),
                queryEntity.getProblemDescription(),
                queryEntity.getPrescriptionMedication(),
                queryEntity.getDosageAndRecautions(),
                queryEntity.getStatus()
        );
    }
}
