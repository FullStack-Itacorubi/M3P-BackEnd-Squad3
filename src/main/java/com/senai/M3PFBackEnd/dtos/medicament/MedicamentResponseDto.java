package com.senai.M3PFBackEnd.dtos.medicament;

import com.senai.M3PFBackEnd.entities.MedicamentEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public record MedicamentResponseDto(
        Long id,

        String name,

        LocalDate date,

        LocalTime time,

        String type,

        Double quantity,

        String unit,

        String observations,

        Boolean status
) {
    public MedicamentResponseDto(MedicamentEntity medicament) {
        this(
                medicament.getId(),
                medicament.getName(),
                medicament.getDate(),
                medicament.getTime(),
                medicament.getType().getType(),
                medicament.getQuantity(),
                medicament.getUnit().getUnity(),
                medicament.getObservations(),
                medicament.getStatus()
        );
    }
}
