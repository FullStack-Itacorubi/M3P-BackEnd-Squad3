package com.senai.M3PFBackEnd.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.senai.M3PFBackEnd.dtos.medicalRecord.MedicalRecordResponseDto;
import com.senai.M3PFBackEnd.services.MedicalRecordService;

@RestController
@RequestMapping("prontuarios")
public class MedicalRecordController {
    
    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping
    public ResponseEntity<List<?>> listMedicalRecords() {
        List<MedicalRecordResponseDto> responseList = medicalRecordService.listMedicalRecords();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
