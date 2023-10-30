package com.senai.M3PFBackEnd.services;

import java.util.List;

import com.senai.M3PFBackEnd.entities.QueryEntity;
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
import com.senai.M3PFBackEnd.repositories.PatientRepository;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordsRepository;

    @Autowired
    PatientRepository patientRepository;

    public void createMedicalRecord(PatientEntity patient) {
        verifyPatientExists(patient.getId());
        MedicalRecordEntity medicalRecord = new MedicalRecordEntity(patient);
        medicalRecordsRepository.save(medicalRecord);
    }

    public List<MedicalRecordResponseDto> listMedicalRecords() {
        List<MedicalRecordEntity> medicalRecords = medicalRecordsRepository
                .findAll();

        return mapListToDto(medicalRecords);
    }

    public List<MedicalRecordResponseDto> listMedicalRecords(String name) {
        List<MedicalRecordEntity> medicalRecords = medicalRecordsRepository
                .findAllByPatientFullNameContainingIgnoringCase(name);

        if (medicalRecords.isEmpty())
            throwBadRequest();

        return mapListToDto(medicalRecords);
    }

    public List<MedicalRecordResponseDto> listMedicalRecords(Long id) {
        verifyPatientHasMedicalRecord(id);
        List<MedicalRecordEntity> medicalRecords = medicalRecordsRepository
                .findAllByPatientId(id);

        if (medicalRecords.isEmpty())
            throwBadRequest();

        return mapListToDto(medicalRecords);
    }

    public List<MedicalRecordResponseDto> listMedicalRecords(Long id, String name) {
        verifyPatientHasMedicalRecord(id);
        List<MedicalRecordEntity> medicalRecords = medicalRecordsRepository
                .findAllByPatientIdAndPatientFullNameContainingIgnoringCase(id, name);

        if (medicalRecords.isEmpty())
            throwBadRequest();

        return mapListToDto(medicalRecords);
    }

    public void addQueriesToPatient(QueryEntity query, Long patientId) {
        verifyPatientHasMedicalRecord(patientId);
        MedicalRecordEntity medicalRecord = medicalRecordsRepository.findAllByPatientId(patientId).get(0);
        medicalRecord.getQueries().add(query);
        medicalRecordsRepository.save(medicalRecord);
    }

    public void addDietToPatient(DietEntity diet, Long patientId) {
        verifyPatientHasMedicalRecord(patientId);
        MedicalRecordEntity medicalRecord = medicalRecordsRepository.findAllByPatientId(patientId).get(0);
        medicalRecord.getDiets().add(diet);
        medicalRecordsRepository.save(medicalRecord);
    }

    public void addExamToPatient(ExamEntity exam, Long patientId) {
        verifyPatientHasMedicalRecord(patientId);
        MedicalRecordEntity medicalRecord = medicalRecordsRepository.findAllByPatientId(patientId).get(0);
        medicalRecord.getExams().add(exam);
        medicalRecordsRepository.save(medicalRecord);
    }

    public void addExerciseToPatient(ExerciseEntity exercise, Long patientId) {
        verifyPatientHasMedicalRecord(patientId);
        MedicalRecordEntity medicalRecord = medicalRecordsRepository.findAllByPatientId(patientId).get(0);
        medicalRecord.getExercises().add(exercise);
        medicalRecordsRepository.save(medicalRecord);
    }

    public void delete(Long patientId) {
        verifyPatientHasMedicalRecord(patientId);
        MedicalRecordEntity medicalRecord = medicalRecordsRepository.findAllByPatientId(patientId).get(0);
        medicalRecordsRepository.delete(medicalRecord);
    }

    public void deleteQueryFromPatient(QueryEntity query, Long patientId) {
        verifyPatientHasMedicalRecord(patientId);
        MedicalRecordEntity medicalRecord = medicalRecordsRepository.findAllByPatientId(patientId).get(0);
        medicalRecord.getQueries().remove(query);
        medicalRecordsRepository.save(medicalRecord);
    }

    public void deleteDietFromPatient(DietEntity diet, Long patientId) {
        verifyPatientHasMedicalRecord(patientId);
        MedicalRecordEntity medicalRecord = medicalRecordsRepository.findAllByPatientId(patientId).get(0);
        medicalRecord.getDiets().remove(diet);
        medicalRecordsRepository.save(medicalRecord);
    }

    public void deleteExamFromPatient(ExamEntity exam, Long patientId) {
        verifyPatientHasMedicalRecord(patientId);
        MedicalRecordEntity medicalRecord = medicalRecordsRepository.findAllByPatientId(patientId).get(0);
        medicalRecord.getExams().remove(exam);
        medicalRecordsRepository.save(medicalRecord);
    }

    public void deleteExerciseFromPatient(ExerciseEntity exercise, Long patientId) {
        verifyPatientHasMedicalRecord(patientId);
        MedicalRecordEntity medicalRecord = medicalRecordsRepository.findAllByPatientId(patientId).get(0);
        medicalRecord.getExercises().remove(exercise);
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

    private void verifyPatientExists(Long id) {
        boolean isPatientIdExists = this.patientRepository.existsById(id);

        if (!isPatientIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    // "O paciente não cadastrado!"
                    "O id do paciente é inválido!");
        }
    }

    private void verifyPatientHasMedicalRecord(Long id) {
        boolean isPatientIdExists = this.medicalRecordsRepository.existsByPatientId(id);

        if (!isPatientIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    // "O paciente não cadastrado!"
                    "Nenhum prontuário encontrado para esse paciente!");
        }
    }
}
