package com.senai.M3PFBackEnd.dtos.medicalRecord;

import java.util.List;
import com.senai.M3PFBackEnd.dtos.Diet.DietResponseDto;
import com.senai.M3PFBackEnd.dtos.query.QueryResponseDto;
import com.senai.M3PFBackEnd.dtos.exam.ExamResponseDto;
import com.senai.M3PFBackEnd.dtos.exercises.ExerciseResponseDto;
import com.senai.M3PFBackEnd.dtos.patient.PatientResponseDto;
import com.senai.M3PFBackEnd.entities.MedicalRecordEntity;

public record MedicalRecordResponseDto(
    Long id,
    PatientResponseDto patient,
    List<ExamResponseDto> exams,
    List<ExerciseResponseDto> exercises,
    List<QueryResponseDto> queries,
    List<DietResponseDto> diets
) {
    public MedicalRecordResponseDto(MedicalRecordEntity medicalRecord) {
        this(
            medicalRecord.getId(),
            new PatientResponseDto(medicalRecord.getPatient()),
            medicalRecord.getExams().stream().map(ExamResponseDto::new).toList(),
            medicalRecord.getExercises().stream().map(ExerciseResponseDto::new).toList(),
            medicalRecord.getQueries().stream().map(QueryResponseDto::new).toList(),
            medicalRecord.getDiets().stream().map(DietResponseDto::new).toList()
        );
    }
}
