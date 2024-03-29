package com.senai.M3PFBackEnd.controllers;

import com.senai.M3PFBackEnd.dtos.query.QueryRequestDto;
import com.senai.M3PFBackEnd.dtos.query.QueryRequestPutDto;
import com.senai.M3PFBackEnd.dtos.query.QueryResponseDto;
import com.senai.M3PFBackEnd.services.QueryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class QueryController {
    @Autowired
    private QueryService queryService;

    @PostMapping
    public ResponseEntity<QueryResponseDto> registerQuery(
            @RequestBody @Valid QueryRequestDto newQuery,
            @RequestHeader(required = true, name = "userId") Long userId) {
        QueryResponseDto query = this.queryService.save(newQuery, userId);

        return new ResponseEntity<>(query, HttpStatus.CREATED);
    }

    @PutMapping("{idQuery}")
    public ResponseEntity<QueryResponseDto> updateQuery(
            @PathVariable(name = "idQuery") Long id,
            @RequestBody @Valid QueryRequestPutDto queryToUpdate,
            @RequestHeader(required = true, name = "userId") Long userId) {
        QueryResponseDto queryUpdated = this.queryService.update(id, queryToUpdate, userId);

        return new ResponseEntity<>(queryUpdated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<QueryResponseDto>> getQueries(
            @RequestParam(name = "nome", required = false) String name) {

        List<QueryResponseDto> queriesList = this.queryService.getAllQueries(name);

        return new ResponseEntity<>(queriesList, HttpStatus.OK);
    }

    @GetMapping("{idQuery}")
    public ResponseEntity<QueryResponseDto> getQuery(
            @PathVariable(name = "idQuery") Long id) {
        QueryResponseDto query = this.queryService.getQueryById(id);

        return new ResponseEntity<>(query, HttpStatus.OK);
    }

    @DeleteMapping("{idQuery}")
    public ResponseEntity<String> deleteQuery(
            @PathVariable(name = "idQuery") Long id,
            @RequestHeader(required = true, name = "patientId") Long patientId,
            @RequestHeader(required = true, name = "userId") Long userId) {
        this.queryService.delete(id, patientId, userId);

        return new ResponseEntity<>(
                "Consulta excluida com sucesso!",
                HttpStatus.ACCEPTED);
    }

}
