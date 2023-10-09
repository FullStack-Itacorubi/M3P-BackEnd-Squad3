package com.senai.M3PFBackEnd.controllers;

import com.senai.M3PFBackEnd.dtos.exam.ExamRequestPostDto;
import com.senai.M3PFBackEnd.dtos.exam.ExamRequestPutDto;
import com.senai.M3PFBackEnd.dtos.exam.ExamResponseDto;
import com.senai.M3PFBackEnd.dtos.user.UserResponseDto;
import com.senai.M3PFBackEnd.services.ExamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exames")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseEntity<ExamResponseDto> registerExam(@RequestBody @Valid ExamRequestPostDto newExam) {
        ExamResponseDto exam = this.examService.save(newExam);
        return new ResponseEntity<>(exam, HttpStatus.CREATED);
    }

    @PutMapping("{idExam}")
    public ResponseEntity<ExamResponseDto> updateExam(@PathVariable(name = "idExam") Long id,
                                                      @RequestBody @Valid ExamRequestPutDto examToUpdate) {
        ExamResponseDto examUpdated = this.examService.update(id, examToUpdate);
        return new ResponseEntity<>(examUpdated, HttpStatus.OK);
    }

    //TODO: filtar GET pelo nome do Usuário
    @GetMapping
    public ResponseEntity<List<ExamResponseDto>> getExams() {
        List<ExamResponseDto> examsList = this.examService.getAllExams();
        return new ResponseEntity<>(examsList, HttpStatus.OK);
    }

    @GetMapping("{idExam}")
    public ResponseEntity<ExamResponseDto> getExam(@PathVariable(name = "idExam") Long id) {
        ExamResponseDto exam = this.examService.getExamById(id);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @DeleteMapping("{idExam}")
    public ResponseEntity<String> deleteExam(@PathVariable(name = "idExam") Long id) {
        this.examService.delete(id);
        return new ResponseEntity<>(
                "Exame excluído com sucesso!",
                HttpStatus.ACCEPTED
        );
    }

}
