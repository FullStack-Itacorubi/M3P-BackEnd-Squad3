package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.medicament.MedicamentRequestPostDto;
import com.senai.M3PFBackEnd.dtos.medicament.MedicamentRequestPutDto;
import com.senai.M3PFBackEnd.dtos.medicament.MedicamentResponseDto;
import com.senai.M3PFBackEnd.entities.MedicamentEntity;
import com.senai.M3PFBackEnd.mappers.MedicamentMapper;
import com.senai.M3PFBackEnd.repositories.MedicamentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MedicamentService {
    @Autowired
    private MedicamentRepository medicamentRepository;

    @Autowired
    private LogsService logsService;

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

        MedicamentEntity medicament = MedicamentMapper.map(medicamentToUpdate);

        medicament.setId(id);

        medicament = this.medicamentRepository.save(medicament);

        logsService.saveLog("O usuário de id " + userId + " alterou o medicamento: " + medicament.getName() + "("
                + medicament.getId() + ")");

        return new MedicamentResponseDto(medicament);
    }

    public List<MedicamentResponseDto> getAll(String name) {
        return this.medicamentRepository
                .findAll()
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
