package com.senai.M3PFBackEnd.dtos.query;

import com.senai.M3PFBackEnd.dtos.medicament.MedicamentResponseDto;
import com.senai.M3PFBackEnd.entities.QueryEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record QueryResponseDto(
        Long id,
        String reasonForConsultation,
        LocalDate consultationDate,
        LocalTime consultationTime,
        String problemDescription,
        List<MedicamentResponseDto> medicaments,
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
                queryEntity.getMedicaments().stream().map(MedicamentResponseDto::new).toList(),
                queryEntity.getDosageAndRecautions(),
                queryEntity.getStatus()
        );
    }
}
