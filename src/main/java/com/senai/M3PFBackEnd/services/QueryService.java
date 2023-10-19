package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.query.QueryRequestDto;
import com.senai.M3PFBackEnd.dtos.query.QueryRequestPutDto;
import com.senai.M3PFBackEnd.dtos.query.QueryResponseDto;
import com.senai.M3PFBackEnd.entities.QueryEntity;
import com.senai.M3PFBackEnd.mappers.QueryMapper;
import com.senai.M3PFBackEnd.repositories.MedicalRecordRepository;
import com.senai.M3PFBackEnd.repositories.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Service
public class QueryService {
    @Autowired
    private QueryRepository queryRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    LogsService logsService;

    private void verifyIsHasId(Long id) {
        boolean isIdExists = this.queryRepository.existsById(id);

        if (!isIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id informado é inválido!");
        }
    }

    private QueryEntity getQuery(Long id) {
        return this.queryRepository.getReferenceById(id);
    }

    public QueryResponseDto save(QueryRequestDto newQuery, Long userId) {

        QueryEntity query = QueryMapper.map(newQuery);

        query = queryRepository.save(query);
        medicalRecordService.addQueriesToPatient(query, newQuery.patientId());

        logsService
                .saveLog("O usuário de id " + userId + " criou uma nova consulta: " + query.getReasonForConsultation()
                        + "("
                        + query.getId() + ")");

        return new QueryResponseDto(query);
    }

    public QueryResponseDto update(Long id, QueryRequestPutDto queryToUpdate, Long userId) {
        this.verifyIsHasId(id);

        QueryEntity query = QueryMapper.map(queryToUpdate);

        query.setId(id);

        query = queryRepository.save(query);

        logsService
                .saveLog("O usuário de id " + userId + " alterou a consulta: " + query.getReasonForConsultation() + "("
                        + query.getId() + ")");

        return new QueryResponseDto(query);
    }

    public List<QueryResponseDto> getAllQueries(String name) {
        if (!name.isBlank()) {
            List<QueryEntity> queries = medicalRecordRepository
                    .findAllByPatientFullNameContainingIgnoringCase(name)
                    .stream().map(r -> r.getQueries()).flatMap(Collection::stream).toList();
            return queries.stream().map(QueryResponseDto::new).toList();
        }
        return queryRepository.findAll().stream().map(QueryResponseDto::new).toList();
    }

    public QueryResponseDto getQueryById(Long id) {
        this.verifyIsHasId(id);

        return new QueryResponseDto(queryRepository.getReferenceById(id));
    }

    public void delete(Long id, Long userId) {
        this.verifyIsHasId(id);

        queryRepository.deleteById(id);
        logsService.saveLog("O usuário de id " + userId + " excluiu a consulta de id: " + id);
    }

}
