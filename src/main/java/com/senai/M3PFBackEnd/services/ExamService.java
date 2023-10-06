package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.exam.ExamRequestDto;
import com.senai.M3PFBackEnd.dtos.exam.ExamResponseDto;
import com.senai.M3PFBackEnd.entities.ExamEntity;
import com.senai.M3PFBackEnd.mappers.ExamMapper;
import com.senai.M3PFBackEnd.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public ExamResponseDto save(ExamRequestDto newExam) {
        ExamEntity exam = ExamMapper.map(newExam);
        return new ExamResponseDto(this.examRepository.save(exam));
    }

}
