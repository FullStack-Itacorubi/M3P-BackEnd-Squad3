package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.exam.ExamRequestPostDto;
import com.senai.M3PFBackEnd.dtos.exam.ExamRequestPutDto;
import com.senai.M3PFBackEnd.dtos.exam.ExamResponseDto;
import com.senai.M3PFBackEnd.entities.ExamEntity;
import com.senai.M3PFBackEnd.mappers.ExamMapper;
import com.senai.M3PFBackEnd.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public ExamResponseDto save(ExamRequestPostDto newExam) {
        ExamEntity exam = ExamMapper.map(newExam);
        return new ExamResponseDto(this.examRepository.save(exam));
    }

    private void verifyIsHasId(Long id) {
        boolean isIdExists = this.examRepository.existsById(id);

        if (!isIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id informado é inválido!"
            );
        }
    }

    public ExamResponseDto update(Long id, ExamRequestPutDto examToUpdate){
        this.verifyIsHasId(id);
        ExamEntity exam = ExamMapper.map(examToUpdate);
        exam.setId(id);
        return new ExamResponseDto(examRepository.save(exam));
    }

}
