package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.diet.DietRequestDto;
import com.senai.M3PFBackEnd.dtos.diet.DietRequestPutDto;
import com.senai.M3PFBackEnd.dtos.diet.DietResponseDto;
import com.senai.M3PFBackEnd.entities.DietEntity;
import com.senai.M3PFBackEnd.mappers.DietMapper;
import com.senai.M3PFBackEnd.repositories.DietRepository;
import com.senai.M3PFBackEnd.repositories.MedicalRecordRepository;
import com.senai.M3PFBackEnd.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Service
public class DietService {
    @Autowired
    private DietRepository dietRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private LogsService logsService;

    private void verifyIsHasId(Long id) {
        boolean isIdExists = this.dietRepository.existsById(id);

        if (!isIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id informado é inválido!");
        }
    }

    private void verifyPatientIdExists(Long id) {
        boolean isPatientIdExists = this.patientRepository.existsById(id);

        if (!isPatientIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id do paciente é inválido!");
        }
    }

    private DietEntity getDiet(Long id) {
        return this.dietRepository.getReferenceById(id);
    }

    public DietResponseDto save(DietRequestDto newDiet, Long userId) {
        verifyPatientIdExists(newDiet.patientId());

        DietEntity diet = DietMapper.map(newDiet);
        diet = dietRepository.save(diet);

        logsService.saveLog("O usuário de id " + userId + " criou um novo exame: " + diet.getDietName() + "("
                + diet.getId() + ")");

        medicalRecordService.addDietToPatient(diet, newDiet.patientId());

        return new DietResponseDto(diet);
    }

    public DietResponseDto update(Long id, DietRequestPutDto dietToUpdate, Long userId) {
        this.verifyIsHasId(id);

        DietEntity diet = DietMapper.map(dietToUpdate);

        DietEntity found = getDiet(id);

        diet.setId(id);
        diet.setDietDate(found.getDietDate());
        diet.setDietTime(found.getDietTime());

        diet = dietRepository.save(diet);

        logsService.saveLog("O usuário de id " + userId + " alterou a dieta: " + diet.getDietName() + "("
                + diet.getId() + ")");

        return new DietResponseDto(diet);
    }

    public List<DietResponseDto> getAllDiets(String name) {
        if (name != null && !name.isBlank()) {
            List<DietEntity> diets = medicalRecordRepository
                    .findAllByPatientFullNameContainingIgnoringCase(name)
                    .stream().map(r -> r.getDiets()).flatMap(Collection::stream).toList();
            return diets.stream().map(DietResponseDto::new).toList();
        }
        return dietRepository.findAll().stream().map(DietResponseDto::new).toList();
    }

    public DietResponseDto getDietById(Long id) {
        this.verifyIsHasId(id);
        return new DietResponseDto(dietRepository.getReferenceById(id));
    }

    public void delete(Long id, Long patientId, Long userId) {
        this.verifyIsHasId(id);
        medicalRecordService.deleteDietFromPatient(dietRepository.getReferenceById(id), patientId);
        dietRepository.deleteById(id);
        logsService.saveLog("O usuário de id " + userId + " excluiu a dieta de id: " + id);
    }
}
