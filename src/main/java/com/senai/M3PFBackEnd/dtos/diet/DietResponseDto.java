package com.senai.M3PFBackEnd.dtos.diet;

import com.senai.M3PFBackEnd.entities.DietEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public record DietResponseDto(
        Long id,
        String dietName,
        LocalDate dietDate,
        LocalTime dietTime,
        String type,
        String description,
        Boolean status
) {
    public DietResponseDto(DietEntity dietEntity) {
        this(
                dietEntity.getId(),
                dietEntity.getDietName(),
                dietEntity.getDietDate(),
                dietEntity.getDietTime(),
                dietEntity.getType().getType(),
                dietEntity.getDescription(),
                dietEntity.getStatus()
        );
    }
}
