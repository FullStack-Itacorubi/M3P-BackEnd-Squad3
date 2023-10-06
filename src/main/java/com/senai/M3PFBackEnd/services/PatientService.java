package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.patient.PatientRequestPostDto;
import com.senai.M3PFBackEnd.dtos.patient.PatientResponseDto;
import com.senai.M3PFBackEnd.entities.PatientEntity;
import com.senai.M3PFBackEnd.mappers.PatientMapper;
import com.senai.M3PFBackEnd.repositories.AddressRepository;
import com.senai.M3PFBackEnd.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;

    private void verifyIfHasCpf(String cpf) {
        boolean isCpfAlreadyExists = this.patientRepository.existsByCpf(cpf);

        if (isCpfAlreadyExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este CPF já foi registrado em nossa base de dados!"
            );
        }
    }

    private void verifyIfHasEmail(String email) {
        boolean isEmailAlreadyExists = this.patientRepository.existsByEmail(email);

        if (isEmailAlreadyExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este e-mail já foi registrado em nossa base de dados!"
            );
        }
    }

    public boolean verifyIdExists(Long id) {
        return this.patientRepository.existsById(id);
    }

    public PatientResponseDto addPatient(PatientRequestPostDto newPatient) {
        verifyIfHasCpf(newPatient.cpf());
        verifyIfHasEmail(newPatient.email());

        PatientEntity patient = PatientMapper.map(newPatient);

        return new PatientResponseDto(this.patientRepository.save(patient));
    }
}
