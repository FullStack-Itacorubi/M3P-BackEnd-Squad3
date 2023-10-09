package com.senai.M3PFBackEnd.controllers;


import com.senai.M3PFBackEnd.dtos.Query.QueryRequestDto;
import com.senai.M3PFBackEnd.dtos.Query.QueryRequestPutDto;
import com.senai.M3PFBackEnd.dtos.Query.QueryResponseDto;
import com.senai.M3PFBackEnd.services.QueryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class QueryController {
    @Autowired
    private QueryService queryService;


    @PostMapping
    public ResponseEntity<QueryResponseDto> registerQuery(
            @RequestBody @Valid QueryRequestDto newQuery
    ) {
        QueryResponseDto query = this.queryService.save(newQuery);

        return new ResponseEntity<>(query, HttpStatus.CREATED);
    }


    @PutMapping("{idQuery}")
    public ResponseEntity<QueryResponseDto> updateQuery(
            @PathVariable(name = "idQuery") Long id,
            @RequestBody @Valid QueryRequestPutDto queryToUpdate
    ){
        QueryResponseDto queryUpdated = this.queryService.update(id, queryToUpdate);

        return new ResponseEntity<>(queryUpdated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllQueries() {
        return ResponseEntity.status(HttpStatus.OK).body(queryService.findAll());
    }

    @GetMapping("{idQuery}")
    public ResponseEntity<QueryResponseDto> getQuery(
            @PathVariable(name = "idQuery") Long id
    ) {
        QueryResponseDto query = this.queryService.getQueryById(id);

        return new ResponseEntity<>(query, HttpStatus.OK);
    }


    @DeleteMapping("{idQuery}")
    public ResponseEntity<String> deleteQuery(
            @PathVariable(name = "idQuery") Long id
    ) {
        this.queryService.delete(id);

        return new ResponseEntity<>(
                "Consulta excluida com sucesso!",
                HttpStatus.ACCEPTED
        );
    }

}
