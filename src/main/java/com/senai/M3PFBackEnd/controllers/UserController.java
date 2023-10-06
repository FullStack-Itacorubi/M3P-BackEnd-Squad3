package com.senai.M3PFBackEnd.controllers;


import com.senai.M3PFBackEnd.dtos.login.*;
import com.senai.M3PFBackEnd.dtos.user.UserRequestPostDto;
import com.senai.M3PFBackEnd.dtos.user.UserRequestPutDto;
import com.senai.M3PFBackEnd.dtos.user.UserResponseDto;
import com.senai.M3PFBackEnd.services.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(
            @RequestBody @Valid UserRequestPostDto newUser
    ) {
        UserResponseDto user = this.userService.save(newUser);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("{idUser}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable(name = "idUser") Long id,
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
            @PathVariable(name = "idUser") Long id
    ) {
        UserResponseDto user = this.userService.getOne(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("{idUser}")
    public ResponseEntity<String> deleteUser(
            @PathVariable(name = "idUser") Long id
    ) {
        this.userService.delete(id);

        return new ResponseEntity<>(
                "Usuário excluído com sucesso!",
                HttpStatus.ACCEPTED
        );
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto newLogin){
        LoginResponseDto verifiedUser = this.userService.login(newLogin);
        return new ResponseEntity<>(verifiedUser, HttpStatus.OK);
    }

    @PatchMapping("resetarsenha")
    public ResponseEntity<PasswordResetResponseDto> resetPassword(
            @RequestBody @Valid PasswordResetRequestDto passwordResetRequestDto){
        return new ResponseEntity<>(this.userService.updatePassword(passwordResetRequestDto), HttpStatus.OK);
    }

    @GetMapping("email")
    public ResponseEntity<PasswordResetResponseDto> getEmail(@RequestBody EmailRequestDto emailRequestDto) {
        PasswordResetResponseDto user = this.userService.getByEmail(emailRequestDto.email());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}