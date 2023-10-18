package com.senai.M3PFBackEnd.controllers;

import com.senai.M3PFBackEnd.dtos.diet.DietRequestDto;
import com.senai.M3PFBackEnd.dtos.diet.DietRequestPutDto;
import com.senai.M3PFBackEnd.dtos.diet.DietResponseDto;
import com.senai.M3PFBackEnd.services.DietService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dietas")
public class DietController {

    @Autowired
    private DietService dietService;

    @PostMapping
    public ResponseEntity<DietResponseDto> registerDiet(
            @RequestBody @Valid DietRequestDto newDiet,
            @RequestHeader(required = true, name = "userId") Long userId) {
        DietResponseDto diet = this.dietService.save(newDiet, userId);

        return new ResponseEntity<>(diet, HttpStatus.CREATED);
    }

    @PutMapping("{idDiet}")
    public ResponseEntity<DietResponseDto> updateDiet(
            @PathVariable(name = "idDiet") Long id,
            @RequestBody @Valid DietRequestPutDto dietToUpdate,
            @RequestHeader(required = true, name = "userId") Long userId) {
        DietResponseDto dietUpdated = this.dietService.update(id, dietToUpdate, userId);

        return new ResponseEntity<>(dietUpdated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DietResponseDto>> getDiets(@RequestParam(name = "nome") String name) {
        List<DietResponseDto> dietsList = this.dietService.getAllDiets(name);
        return new ResponseEntity<>(dietsList, HttpStatus.OK);
    }

    @GetMapping("{idDiet}")
    public ResponseEntity<DietResponseDto> getDiet(
            @PathVariable(name = "idDiet") Long id) {
        DietResponseDto diet = this.dietService.getDietById(id);

        return new ResponseEntity<>(diet, HttpStatus.OK);
    }

    @DeleteMapping("{idDiet}")
    public ResponseEntity<String> deleteDiet(
            @PathVariable(name = "idDiet") Long id,
            @RequestHeader(required = true, name = "userId") Long userId) {
        this.dietService.delete(id, userId);

        return new ResponseEntity<>(
                "Dieta excluida com sucesso!",
                HttpStatus.ACCEPTED);
    }

}
