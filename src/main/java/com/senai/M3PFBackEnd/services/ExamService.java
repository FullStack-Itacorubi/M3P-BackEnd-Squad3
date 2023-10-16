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

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    LogsService logsService;

    public ExamResponseDto save(ExamRequestPostDto newExam, Long userId) {
        ExamEntity exam = ExamMapper.map(newExam);
        exam = this.examRepository.save(exam);

        logsService.saveLog("O usuário de id " + userId + " criou um novo exame: " + exam.getExamName() + "("
                + exam.getId() + ")");
        return new ExamResponseDto(exam);
    }

    private void verifyIsHasId(Long id) {
        boolean isIdExists = this.examRepository.existsById(id);

        if (!isIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id informado é inválido!");
        }
    }

    public ExamResponseDto update(Long id, ExamRequestPutDto examToUpdate, Long userId) {
        this.verifyIsHasId(id);
        ExamEntity exam = ExamMapper.map(examToUpdate);
        exam.setId(id);
        exam = examRepository.save(exam);

        logsService.saveLog("O usuário de id " + userId + " alterou o exame: " + exam.getExamName() + "("
                + exam.getId() + ")");
        return new ExamResponseDto(exam);
    }

    // TODO: filtar GET pelo nome do Usuário
    public List<ExamResponseDto> getAllExams() {
        return this.examRepository
                .findAll()
                .stream()
                .map(ExamResponseDto::new)
                .toList();
    }

    public ExamResponseDto getExamById(Long id) {
        this.verifyIsHasId(id);
        return new ExamResponseDto(examRepository.getReferenceById(id));
    }

    public void delete(Long id, Long userId) {
        this.verifyIsHasId(id);
        examRepository.deleteById(id);
        logsService.saveLog("O usuário de id " + userId + " excluiu o exame de id: " + id);
    }

}
