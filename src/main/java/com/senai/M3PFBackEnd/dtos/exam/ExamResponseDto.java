package com.senai.M3PFBackEnd.dtos.exam;

import com.senai.M3PFBackEnd.entities.ExamEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public record ExamResponseDto(
        Long id,
        String examName,
        LocalDate examDate,
        LocalTime examHour,
        String examType,
        String laboratory,
        String documentUrl,
        String results
) {
    public ExamResponseDto(ExamEntity examEntity){
        this(
                examEntity.getId(),
                examEntity.getExamName(),
                examEntity.getExamDate(),
                examEntity.getExamHour(),
                examEntity.getExamType(),
                examEntity.getLaboratory(),
                examEntity.getDocumentUrl(),
                examEntity.getResults()
        );
    }
}
