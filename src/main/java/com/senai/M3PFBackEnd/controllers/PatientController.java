package com.senai.M3PFBackEnd.controllers;

import com.senai.M3PFBackEnd.dtos.patient.PatientRequestPostDto;
import com.senai.M3PFBackEnd.dtos.patient.PatientRequestPutDto;
import com.senai.M3PFBackEnd.dtos.patient.PatientResponseDto;
import com.senai.M3PFBackEnd.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDto> registerPatient(
            @RequestBody @Valid PatientRequestPostDto newPatient,
            @RequestHeader(required = true, name = "userId") Long userId) {
        PatientResponseDto patient = this.patientService.addPatient(newPatient, userId);

        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping("{idPatient}")
    public ResponseEntity<PatientResponseDto> updatePatient(
            @PathVariable(name = "idPatient") Long id,
            @RequestBody @Valid PatientRequestPutDto patientToUpdate,
            @RequestHeader(required = true, name = "userId") Long userId) {
        PatientResponseDto patientUpdated = this.patientService.update(id, patientToUpdate, userId);

        return new ResponseEntity<>(patientUpdated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
        @RequestParam(name = "s", required = false) String filter
    ) {
        List<PatientResponseDto> patientsList;
        if(filter == null)
            patientsList = this.patientService.getAll();
        else
            patientsList = this.patientService.getAll(filter);

        return new ResponseEntity<>(patientsList, HttpStatus.OK);
    }

    @GetMapping("{idPatient}")
    public ResponseEntity<PatientResponseDto> getPatient(
            @PathVariable(name = "idPatient") Long id) {
        PatientResponseDto patient = this.patientService.getOne(id);

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @DeleteMapping("{idPatient}")
    public ResponseEntity<String> deletePatient(
            @PathVariable(name = "idPatient") Long id,
            @RequestHeader(required = true, name = "userId") Long userId) {
        this.patientService.delete(id, userId);

        return new ResponseEntity<>(
                "Usuário excluído com sucesso!",
                HttpStatus.ACCEPTED);
    }
}
