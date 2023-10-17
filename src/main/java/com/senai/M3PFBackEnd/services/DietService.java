package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.diet.DietRequestDto;
import com.senai.M3PFBackEnd.dtos.diet.DietRequestPutDto;
import com.senai.M3PFBackEnd.dtos.diet.DietResponseDto;
import com.senai.M3PFBackEnd.entities.DietEntity;
import com.senai.M3PFBackEnd.mappers.DietMapper;
import com.senai.M3PFBackEnd.repositories.DietRepository;
import com.senai.M3PFBackEnd.repositories.MedicalRecordRepository;
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


    private void verifyIsHasId(Long id) {
        boolean isIdExists = this.dietRepository.existsById(id);

        if (!isIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id informado é inválido!"
            );
        }
    }

    private DietEntity getDiet(Long id) {
        return this.dietRepository.getReferenceById(id);
    }


    public DietResponseDto save(DietRequestDto newDiet) {

        DietEntity diet = DietMapper.map(newDiet);

        diet = dietRepository.save(diet);
        medicalRecordService.addDietToPatient(diet, newDiet.patientId());

        return new DietResponseDto(diet);
    }

    public DietResponseDto update(Long id, DietRequestPutDto dietToUpdate) {
        this.verifyIsHasId(id);

        DietEntity diet = DietMapper.map(dietToUpdate);

        DietEntity found = getDiet(id);

        diet.setId(id);
        diet.setDietDate(found.getDietDate());
        diet.setDietTime(found.getDietTime());

        return new DietResponseDto(dietRepository.save(diet));
    }

    public List<DietResponseDto> getAllDiets(String name) {
        if (!name.isBlank()) {
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

    public void delete(Long id) {
        this.verifyIsHasId(id);
        dietRepository.deleteById(id);
    }


}
