package com.senai.M3PFBackEnd.controllers;

import com.senai.M3PFBackEnd.dtos.Diet.DietRequestDto;
import com.senai.M3PFBackEnd.dtos.Diet.DietRequestPutDto;
import com.senai.M3PFBackEnd.dtos.Diet.DietResponseDto;
import com.senai.M3PFBackEnd.services.DietService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dietas")
public class DietController {

    @Autowired
    private DietService dietService;


    @PostMapping
    public ResponseEntity<DietResponseDto> registerDiet(
            @RequestBody @Valid DietRequestDto newDiet
    ) {
        DietResponseDto diet = this.dietService.save(newDiet);

        return new ResponseEntity<>(diet, HttpStatus.CREATED);
    }


    @PutMapping("{idDiet}")
    public ResponseEntity<DietResponseDto> updateDiet(
            @PathVariable(name = "idDiet") Long id,
            @RequestBody @Valid DietRequestPutDto dietToUpdate
    ){
        DietResponseDto dietUpdated = this.dietService.update(id, dietToUpdate);

        return new ResponseEntity<>(dietUpdated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllDiets() {
        return ResponseEntity.status(HttpStatus.OK).body(dietService.findAll());
    }


    @GetMapping("{idDiet}")
    public ResponseEntity<DietResponseDto> getDiet(
            @PathVariable(name = "idDiet") Long id
    ) {
        DietResponseDto diet = this.dietService.getOne(id);

        return new ResponseEntity<>(diet, HttpStatus.OK);
    }


    @DeleteMapping("{idDiet}")
    public ResponseEntity<String> deleteDiet(
            @PathVariable(name = "idDiet") Long id
    ) {
        this.dietService.delete(id);

        return new ResponseEntity<>(
                "Consulta excluida com sucesso!",
                HttpStatus.ACCEPTED
        );
    }

}
