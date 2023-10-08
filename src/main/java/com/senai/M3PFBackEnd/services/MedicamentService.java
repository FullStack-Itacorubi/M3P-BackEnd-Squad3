package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.medicament.MedicamentRequestPostDto;
import com.senai.M3PFBackEnd.dtos.medicament.MedicamentRequestPutDto;
import com.senai.M3PFBackEnd.dtos.medicament.MedicamentResponseDto;
import com.senai.M3PFBackEnd.entities.MedicamentEntity;
import com.senai.M3PFBackEnd.entities.UserEntity;
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

    private void verifyIfHasId(Long id) {
        boolean isIdExists = this.medicamentRepository.existsById(id);

        if (!isIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id informado é inválido!"
            );
        }
    }

    private MedicamentEntity getMedicament(Long id) {
        return this.medicamentRepository.getReferenceById(id);
    }

    public MedicamentResponseDto save(MedicamentRequestPostDto newMedicament) {
        MedicamentEntity medicament = MedicamentMapper.map(newMedicament);

        return new MedicamentResponseDto(this.medicamentRepository.save(medicament));
    }

    public MedicamentResponseDto update(Long id, MedicamentRequestPutDto medicamentToUpdate) {
        this.verifyIfHasId(id);

        // MedicamentEntity medicamentFound = getMedicament(id);
        MedicamentEntity medicament = MedicamentMapper.map(medicamentToUpdate);

        medicament.setId(id);

        return new MedicamentResponseDto(this.medicamentRepository.save(medicament));
    }

    public List<MedicamentResponseDto> getAll(String name) {
        return this.medicamentRepository
                .findAll()
                .stream()
                .map(MedicamentResponseDto::new)
                .toList();
    }

    public MedicamentResponseDto getOne(Long id) {
        this.verifyIfHasId(id);

        /*MedicamentEntity medicament = this.getMedicament(id);

        return new MedicamentResponseDto(medicament);*/
        return new MedicamentResponseDto(this.medicamentRepository.getReferenceById(id));
    }

    public void delete(Long id) {
        this.verifyIfHasId(id);

        this.medicamentRepository.deleteById(id);
    }
}
