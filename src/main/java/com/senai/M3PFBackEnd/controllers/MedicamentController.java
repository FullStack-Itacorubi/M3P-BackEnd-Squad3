package com.senai.M3PFBackEnd.controllers;

import com.senai.M3PFBackEnd.dtos.medicament.MedicamentRequestPutDto;
import com.senai.M3PFBackEnd.dtos.medicament.MedicamentRequestPostDto;
import com.senai.M3PFBackEnd.dtos.medicament.MedicamentResponseDto;
import com.senai.M3PFBackEnd.services.MedicamentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicamentos")
public class MedicamentController {
    @Autowired
    private MedicamentService medicamentService;

    @PostMapping
    public ResponseEntity<MedicamentResponseDto> registerMedicament(
            @RequestBody @Valid MedicamentRequestPostDto newMedicament,
            @RequestHeader(required = true, name = "userId") Long userId) {
        MedicamentResponseDto medicament = this.medicamentService.save(newMedicament, userId);

        return new ResponseEntity<>(medicament, HttpStatus.CREATED);
    }

    @PutMapping("{idMedicament}")
    public ResponseEntity<MedicamentResponseDto> updateMedicament(
            @PathVariable(name = "idMedicament") Long id,
            @RequestBody @Valid MedicamentRequestPutDto medicamentToUpdate,
            @RequestHeader(required = true, name = "userId") Long userId) {
        MedicamentResponseDto medicamentUpdated = this.medicamentService.update(id, medicamentToUpdate, userId);

        return new ResponseEntity<>(medicamentUpdated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MedicamentResponseDto>> getAllMedicaments(
            @RequestParam(name = "nome", required = false) String name) {
        List<MedicamentResponseDto> medicamentList = this.medicamentService.getAll(name);

        return new ResponseEntity<>(medicamentList, HttpStatus.OK);
    }

    @GetMapping("{idMedicament}")
    public ResponseEntity<MedicamentResponseDto> getMedicament(
            @PathVariable(name = "idMedicament") Long id) {
        MedicamentResponseDto medicament = this.medicamentService.getMedicamentById(id);

        return new ResponseEntity<>(medicament, HttpStatus.OK);
    }

    @DeleteMapping("{idMedicament}")
    public ResponseEntity<String> deleteMedicament(
            @PathVariable(name = "idMedicament") Long id,
            @RequestHeader(required = true, name = "userId") Long userId) {
        this.medicamentService.delete(id, userId);

        return new ResponseEntity<>(
                "Medicamento excluído com sucesso!",
                HttpStatus.ACCEPTED);
    }
}
