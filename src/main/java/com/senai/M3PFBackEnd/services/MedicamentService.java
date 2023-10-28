package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.medicament.MedicamentRequestPostDto;
import com.senai.M3PFBackEnd.dtos.medicament.MedicamentRequestPutDto;
import com.senai.M3PFBackEnd.dtos.medicament.MedicamentResponseDto;
import com.senai.M3PFBackEnd.entities.MedicamentEntity;
import com.senai.M3PFBackEnd.entities.QueryEntity;
import com.senai.M3PFBackEnd.mappers.MedicamentMapper;
import com.senai.M3PFBackEnd.repositories.MedicalRecordRepository;
import com.senai.M3PFBackEnd.repositories.MedicamentRepository;
import com.senai.M3PFBackEnd.entities.MedicalRecordEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Service
public class MedicamentService {
    @Autowired
    private MedicamentRepository medicamentRepository;

    @Autowired
    private LogsService logsService;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    private void verifyIfHasId(Long id) {
        boolean isIdExists = this.medicamentRepository.existsById(id);

        if (!isIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id informado é inválido!");
        }
    }

    public MedicamentResponseDto save(MedicamentRequestPostDto newMedicament, Long userId) {
        MedicamentEntity medicament = MedicamentMapper.map(newMedicament);
        medicament = this.medicamentRepository.save(medicament);

        logsService.saveLog("O usuário de id " + userId + " criou um novo medicamento: " + medicament.getName() + "("
                + medicament.getId() + ")");

        return new MedicamentResponseDto(medicament);
    }

    public MedicamentResponseDto update(Long id, MedicamentRequestPutDto medicamentToUpdate, Long userId) {
        this.verifyIfHasId(id);
        MedicamentEntity oldMedicament = medicamentRepository.getReferenceById(id);
        MedicamentEntity medicament = MedicamentMapper.map(medicamentToUpdate);

        medicament.setId(id);
        medicament.setDate(oldMedicament.getDate());
        medicament.setTime(oldMedicament.getTime());

        medicament = this.medicamentRepository.save(medicament);

        logsService.saveLog("O usuário de id " + userId + " alterou o medicamento: " + medicament.getName() + "("
                + medicament.getId() + ")");

        return new MedicamentResponseDto(medicament);
    }

    public List<MedicamentResponseDto> getAll() {
        return this.medicamentRepository
                .findAll()
                .stream()
                .map(MedicamentResponseDto::new)
                .toList();
    }

    public List<MedicamentResponseDto> getAll(String name) {
        List<QueryEntity> queries = medicalRecordRepository
                .findAllByPatientFullNameContainingIgnoringCase(name)
                .stream()
                .map(MedicalRecordEntity::getQueries)
                .flatMap(Collection::stream)
                .toList();

            List<MedicamentEntity> medicaments = queries
                    .stream()
                    .map(QueryEntity::getMedicaments)
                    .flatMap(Collection::stream)
                    .toList();

            return medicaments
                    .stream()
                    .map(MedicamentResponseDto::new)
                    .toList();
    }

    public MedicamentResponseDto getMedicamentById(Long id) {
        this.verifyIfHasId(id);

        return new MedicamentResponseDto(this.medicamentRepository.getReferenceById(id));
    }

    public void delete(Long id, Long userId) {
        this.verifyIfHasId(id);

        this.medicamentRepository.deleteById(id);
        logsService.saveLog("O usuário de id " + userId + " excluiu o medicamento de id: " + id);
    }
}
