package com.senai.M3PFBackEnd.controllers;

import com.senai.M3PFBackEnd.dtos.medicament.MedicamentRequestPostDto;
import com.senai.M3PFBackEnd.dtos.medicament.MedicamentResponseDto;
import com.senai.M3PFBackEnd.services.MedicamentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicamentos")
public class MedicamentController {
    @Autowired
    private MedicamentService medicamentService;

    @PostMapping
    public ResponseEntity<MedicamentResponseDto> registerMedicament(
            @RequestBody @Valid MedicamentRequestPostDto newMedicament
    ) {
        MedicamentResponseDto medicament = this.medicamentService.save(newMedicament);

        return new ResponseEntity<>(medicament, HttpStatus.CREATED);
    }
}
