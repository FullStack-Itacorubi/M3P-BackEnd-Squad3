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

@RestController
@RequestMapping("/pacientes")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDto> registerPatient(
            @RequestBody @Valid PatientRequestPostDto newPatient
    ) {
        PatientResponseDto patient = this.patientService.addPatient(newPatient);

        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping("{idPatient}")
    public ResponseEntity<PatientResponseDto> updatePatient(
            @PathVariable(name = "idPatient") Long id,
            @RequestBody @Valid PatientRequestPutDto patientToUpdate
    ) {
        PatientResponseDto patientUpdated = this.patientService.update(id, patientToUpdate);

        return new ResponseEntity<>(patientUpdated, HttpStatus.OK);
    }
}
