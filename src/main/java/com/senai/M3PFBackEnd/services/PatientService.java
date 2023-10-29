package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.patient.PatientRequestPostDto;
import com.senai.M3PFBackEnd.dtos.patient.PatientRequestPutDto;
import com.senai.M3PFBackEnd.dtos.patient.PatientResponseDto;
import com.senai.M3PFBackEnd.entities.PatientEntity;
import com.senai.M3PFBackEnd.mappers.PatientMapper;
import com.senai.M3PFBackEnd.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private LogsService logsService;

    public PatientResponseDto save(PatientRequestPostDto newPatient, Long userId) {
        verifyIfHasCpf(newPatient.cpf());
        verifyIfHasEmail(newPatient.email());

        PatientEntity patient = PatientMapper.map(newPatient);
        patient = this.patientRepository.save(patient);
        logsService.saveLog("O usuário de id " + userId + " criou um novo paciente: " + patient.getFullName() + "("
                + patient.getId() + ")");
        medicalRecordService.createMedicalRecord(patient);

        return new PatientResponseDto(patient);
    }

    public PatientResponseDto update(Long id, PatientRequestPutDto patientToUpdate, Long userId) {
        verifyIfHasId(id);
        checkEmailUpdate(id, patientToUpdate.email());

        var patientFound = this.patientRepository.getReferenceById(id);
        var patient = PatientMapper.map(patientToUpdate);

        patient.setId(patientFound.getId());
        patient.setCpf(patientFound.getCpf());
        patient.setRg(patientFound.getRg());
        patient.getAddress().setId(patientFound.getAddress().getId());
        patient = this.patientRepository.save(patient);
        logsService.saveLog("O usuário de id " + userId + " alterou o paciente: " + patient.getFullName() + "("
                + patient.getId() + ")");

        return new PatientResponseDto(patient);
    }

    public List<PatientResponseDto> getAll() {
        return this.patientRepository
                .findAll()
                .stream()
                .map(PatientResponseDto::new)
                .toList();
    }

    public PatientResponseDto getById(Long id) {
        this.verifyIfHasId(id);

        PatientEntity patient = this.getPatient(id);

        return new PatientResponseDto(patient);
    }

    public void delete(Long id, Long userId) {
        this.verifyIfHasId(id);
        this.medicalRecordService.delete(id);
        this.patientRepository.deleteById(id);
        logsService.saveLog("O usuário de id " + userId + " excluiu o paciente de id: " + id);
    }

    private void verifyIfHasEmail(String email) {
        boolean isEmailAlreadyExists = this.patientRepository.existsByEmail(email);
        
        if (isEmailAlreadyExists) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                    "Este e-mail já foi registrado em nossa base de dados!");
        }
    }
    
    private void verifyIfHasId(Long id) {
        boolean isIdExists = this.patientRepository.existsById(id);
    
        if (!isIdExists) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "O id informado é inválido!");
            }
    }
    
    private void checkEmailUpdate(Long id, String email) {
        var patient = this.patientRepository
                .findAll()
                .stream()
                .filter(p -> p.getEmail().equals(email) && !p.getId().equals(id))
                .findFirst();
    
        if (patient.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este e-mail já foi registrado em nossa base de dados!");
        }
    }
    
    private PatientEntity getPatient(Long id) {
        return this.patientRepository.getReferenceById(id);
    }

    private void verifyIfHasCpf(String cpf) {
        boolean isCpfAlreadyExists = this.patientRepository.existsByCpf(cpf);
    
        if (isCpfAlreadyExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este CPF já foi registrado em nossa base de dados!");
        }
    }
}
