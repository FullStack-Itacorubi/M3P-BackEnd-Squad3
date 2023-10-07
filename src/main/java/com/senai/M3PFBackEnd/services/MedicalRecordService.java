package com.senai.M3PFBackEnd.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.senai.M3PFBackEnd.dtos.medicalRecord.MedicalRecordResponseDto;
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

    public List<MedicalRecordResponseDto> listMedicalRecords() {
        return medicalRecordsRepository.findAll().stream()
            .map(MedicalRecordResponseDto::new).toList();
    }
}
