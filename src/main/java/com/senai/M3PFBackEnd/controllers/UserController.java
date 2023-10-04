package com.senai.M3PFBackEnd.controllers;


import com.senai.M3PFBackEnd.dtos.User.UserRequestDto;
import com.senai.M3PFBackEnd.dtos.User.UserRequestPutDto;
import com.senai.M3PFBackEnd.dtos.User.UserResponseDto;
import com.senai.M3PFBackEnd.services.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(
            @RequestBody @Valid UserRequestDto newUser
    ) {
        UserResponseDto user = this.userService.save(newUser);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("{idUser}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable(name = "idUser") UUID id,
            @RequestBody @Valid UserRequestPutDto userToUpdate
    ) {
        UserResponseDto userUpdated = this.userService.update(id, userToUpdate);

        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> usersList = this.userService.getAll();

        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping("{idUser}")
    public ResponseEntity<UserResponseDto> getUser(
            @PathVariable(name = "idUser") UUID id
    ) {
        UserResponseDto user = this.userService.getOne(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("{idUser}")
    public ResponseEntity<String> deleteUser(
            @PathVariable(name = "idUser") UUID id
    ) {
        this.userService.delete(id);

        return new ResponseEntity<>(
                "Usuário excluído com sucesso!",
                HttpStatus.ACCEPTED
        );
    }
}