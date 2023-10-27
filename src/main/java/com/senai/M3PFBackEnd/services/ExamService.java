package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.exam.ExamRequestPostDto;
import com.senai.M3PFBackEnd.dtos.exam.ExamRequestPutDto;
import com.senai.M3PFBackEnd.dtos.exam.ExamResponseDto;
import com.senai.M3PFBackEnd.entities.ExamEntity;
import com.senai.M3PFBackEnd.mappers.ExamMapper;
import com.senai.M3PFBackEnd.repositories.ExamRepository;
import com.senai.M3PFBackEnd.repositories.MedicalRecordRepository;
import com.senai.M3PFBackEnd.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private LogsService logsService;

    private void verifyPatientIdExists(Long id) {
        boolean isPatientIdExists = this.patientRepository.existsById(id);

        if (!isPatientIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id do paciente é inválido!");
        }
    }

    public ExamResponseDto save(ExamRequestPostDto newExam, Long userId) {
        verifyPatientIdExists(newExam.patientId());

        ExamEntity exam = ExamMapper.map(newExam);
        exam = this.examRepository.save(exam);

        logsService.saveLog("O usuário de id " + userId + " criou um novo exame: " + exam.getExamName() + "("
                + exam.getId() + ")");

        medicalRecordService.addExamToPatient(exam, newExam.patientId());

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

    public List<ExamResponseDto> getAllExams(String name) {
        if (name != null && !name.isBlank()) {
            List<ExamEntity> exams = medicalRecordRepository
                    .findAllByPatientFullNameContainingIgnoringCase(name)
                    .stream().map(r -> r.getExams()).flatMap(Collection::stream).toList();
            return exams.stream().map(ExamResponseDto::new).toList();
        }
        return examRepository.findAll().stream().map(ExamResponseDto::new).toList();
    }

    public ExamResponseDto getExamById(Long id) {
        this.verifyIsHasId(id);
        return new ExamResponseDto(examRepository.getReferenceById(id));
    }

    public void delete(Long id, Long patientId, Long userId) {
        this.verifyIsHasId(id);
        medicalRecordService.deleteExamFromPatient(examRepository.getReferenceById(id), patientId);
        examRepository.deleteById(id);
        logsService.saveLog("O usuário de id " + userId + " excluiu o exame de id: " + id);
    }

}
