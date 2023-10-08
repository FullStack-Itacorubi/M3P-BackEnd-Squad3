package com.senai.M3PFBackEnd.services;


import com.senai.M3PFBackEnd.dtos.Query.QueryRequestDto;
import com.senai.M3PFBackEnd.dtos.Query.QueryRequestPutDto;
import com.senai.M3PFBackEnd.dtos.Query.QueryResponseDto;
import com.senai.M3PFBackEnd.entities.QueryEntity;
import com.senai.M3PFBackEnd.mappers.QueryMapper;
import com.senai.M3PFBackEnd.repositories.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class QueryService {
    @Autowired
    private QueryRepository queryRepository;


    private void verifyIsHasId(Long id) {
        boolean isIdExists = this.queryRepository.existsById(id);

        if (!isIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id informado é inválido!"
            );
        }
    }

    private QueryEntity getQuery(Long id) {
        return this.queryRepository.getReferenceById(id);
    }

    public QueryResponseDto save(QueryRequestDto newQuery) {

        QueryEntity query = QueryMapper.map(newQuery);

        return new QueryResponseDto(queryRepository.save(query));
    }


    public QueryResponseDto update(Long id, QueryRequestPutDto queryToUpdate) {
        this.verifyIsHasId(id);

        QueryEntity query = QueryMapper.map(queryToUpdate);

        query.setId(id);

        return new QueryResponseDto(queryRepository.save(query));
    }

    public List<QueryEntity> findAll() {
            return queryRepository.findAll();
    }

    public QueryResponseDto getDietById(Long id) {
        this.verifyIsHasId(id);

        return new QueryResponseDto(queryRepository.getReferenceById(id));
    }

    public void delete(Long id) {
        this.verifyIsHasId(id);

        queryRepository.deleteById(id);
    }


}

