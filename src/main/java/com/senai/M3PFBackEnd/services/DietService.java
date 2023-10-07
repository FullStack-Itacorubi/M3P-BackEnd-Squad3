package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.Diet.DietRequestDto;
import com.senai.M3PFBackEnd.dtos.Diet.DietRequestPutDto;
import com.senai.M3PFBackEnd.dtos.Diet.DietResponseDto;
import com.senai.M3PFBackEnd.entities.DietEntity;
import com.senai.M3PFBackEnd.mappers.DietMapper;
import com.senai.M3PFBackEnd.repositories.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DietService {
    @Autowired
    private DietRepository dietRepository;


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

        return new DietResponseDto(dietRepository.save(diet));
    }

    public DietResponseDto update(Long id, DietRequestPutDto dietToUpdate) {
        this.verifyIsHasId(id);

        DietEntity diet = DietMapper.map(dietToUpdate);

        diet.setId(id);

        return new DietResponseDto(dietRepository.save(diet));
    }

    public List<DietEntity> findAll() {
        return dietRepository.findAll();
    }

    public DietResponseDto getOne(Long id) {
        this.verifyIsHasId(id);

        DietEntity diet = this.getDiet(id);

        return new DietResponseDto(diet);
    }


    public DietEntity delete(Long id) {
        this.verifyIsHasId(id);

        DietEntity dietFound = this.getDiet(id);

        this.dietRepository.delete(dietFound);

        return dietFound;
    }
}
