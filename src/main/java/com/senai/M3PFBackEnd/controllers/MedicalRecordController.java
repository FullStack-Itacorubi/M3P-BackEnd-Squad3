package com.senai.M3PFBackEnd.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.senai.M3PFBackEnd.dtos.medicalRecord.MedicalRecordResponseDto;
import com.senai.M3PFBackEnd.services.MedicalRecordService;

@RestController
@RequestMapping("prontuarios")
public class MedicalRecordController {
    
    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping
    public ResponseEntity<List<MedicalRecordResponseDto>> listMedicalRecords(
        @RequestParam(name = "s", required = false) String name,
        @RequestParam(name = "id", required = false) Long id
    ) {
        List<MedicalRecordResponseDto> responseList;

        if(id != null && name != null) responseList = medicalRecordService.listMedicalRecords(id, name);
        else if(id != null) responseList = medicalRecordService.listMedicalRecords(id);
        else if(name != null) responseList = medicalRecordService.listMedicalRecords(name);
        else responseList = medicalRecordService.listMedicalRecords();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
