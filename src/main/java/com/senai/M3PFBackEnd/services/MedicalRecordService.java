package com.senai.M3PFBackEnd.services;

import java.util.List;

import com.senai.M3PFBackEnd.entities.DietEntity;
import com.senai.M3PFBackEnd.entities.ExamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.senai.M3PFBackEnd.dtos.medicalRecord.MedicalRecordResponseDto;
import com.senai.M3PFBackEnd.entities.ExerciseEntity;
import com.senai.M3PFBackEnd.entities.MedicalRecordEntity;
import com.senai.M3PFBackEnd.entities.PatientEntity;
import com.senai.M3PFBackEnd.repositories.MedicalRecordRepository;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordsRepository;
    
    public void createMedicalRecord(PatientEntity patient) {
        MedicalRecordEntity medicalRecord = new MedicalRecordEntity(patient);
        medicalRecordsRepository.save(medicalRecord);
    }

    public List<MedicalRecordResponseDto> listMedicalRecords(String name) {
        List<MedicalRecordEntity> medicalRecords = medicalRecordsRepository
            .findAllByPatientFullNameContainingIgnoringCase(name);
    
        if(medicalRecords.isEmpty()) throwBadRequest();
    
        return mapListToDto(medicalRecords);
    }

    public List<MedicalRecordResponseDto> listMedicalRecords(Long id) {
        List<MedicalRecordEntity> medicalRecords = medicalRecordsRepository
            .findAllByPatientId(id);
    
        if(medicalRecords.isEmpty()) throwBadRequest();
    
        return mapListToDto(medicalRecords);
    }

    public List<MedicalRecordResponseDto> listMedicalRecords(Long id, String name) {
        List<MedicalRecordEntity> medicalRecords = medicalRecordsRepository
            .findAllByPatientIdAndPatientFullNameContainingIgnoringCase(id, name);

        if(medicalRecords.isEmpty()) throwBadRequest();

        return mapListToDto(medicalRecords);
    }


    public void addDietToPatient(DietEntity diet, Long patientId){
        MedicalRecordEntity medicalRecord = medicalRecordsRepository.findAllByPatientId(patientId).get(0);
        medicalRecord.getDiets().add(diet);
        medicalRecordsRepository.save(medicalRecord);
    }

    public void addExamToPatient(ExamEntity exam, Long patientId){
        MedicalRecordEntity medicalRecord = medicalRecordsRepository.findAllByPatientId(patientId).get(0);
        medicalRecord.getExams().add(exam);
        medicalRecordsRepository.save(medicalRecord);
    }
  
    public void addExerciseToPatient(ExerciseEntity exercise, Long patientId) {
        MedicalRecordEntity medicalRecord = medicalRecordsRepository.findAllByPatientId(patientId).get(0);
        medicalRecord.getExercises().add(exercise);
        medicalRecordsRepository.save(medicalRecord);
    }

    private List<MedicalRecordResponseDto> mapListToDto(List<MedicalRecordEntity> list) {
        return list.stream().map(MedicalRecordResponseDto::new).toList();
    }

    private void throwBadRequest() {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Busca não condiz com nenhum usuário");
    }
}
