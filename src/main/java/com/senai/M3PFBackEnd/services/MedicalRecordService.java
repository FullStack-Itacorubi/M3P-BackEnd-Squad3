package com.senai.M3PFBackEnd.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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

    public List<MedicalRecordResponseDto> listMedicalRecords(String name) {
        List<MedicalRecordEntity> medicalRecords = medicalRecordsRepository
            .findAllByPatientFullNameContaining(name);
    
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
            .findAllByPatientIdAndPatientFullNameContaining(id, name);

        if(medicalRecords.isEmpty()) throwBadRequest();

        return mapListToDto(medicalRecords);
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
