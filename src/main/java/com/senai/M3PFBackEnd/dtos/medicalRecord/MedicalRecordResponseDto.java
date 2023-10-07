package com.senai.M3PFBackEnd.dtos.medicalRecord;

import java.util.List;
import com.senai.M3PFBackEnd.dtos.exam.ExamResponseDto;
import com.senai.M3PFBackEnd.dtos.exercises.ExerciseResponseDto;
import com.senai.M3PFBackEnd.dtos.patient.PatientResponseDto;
import com.senai.M3PFBackEnd.entities.MedicalRecordEntity;

public record MedicalRecordResponseDto(
    Long id,
    PatientResponseDto patient,
    List<ExamResponseDto> exams,
    List<ExerciseResponseDto> exercises
) {
    public MedicalRecordResponseDto(MedicalRecordEntity medicalRecord) {
        this(
            medicalRecord.getId(),
            new PatientResponseDto(medicalRecord.getPatient()),
            medicalRecord.getExams().stream().map(ExamResponseDto::new).toList(),
            medicalRecord.getExercises().stream().map(ExerciseResponseDto::new).toList()
        );
    }
}
