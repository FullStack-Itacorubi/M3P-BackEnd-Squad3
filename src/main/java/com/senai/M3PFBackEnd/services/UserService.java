package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.user.UserRequestDto;
import com.senai.M3PFBackEnd.dtos.user.UserRequestPutDto;
import com.senai.M3PFBackEnd.dtos.user.UserResponseDto;
import com.senai.M3PFBackEnd.entities.UserEntity;
import com.senai.M3PFBackEnd.mappers.UserMapper;
import com.senai.M3PFBackEnd.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private void verifyIfHasCpf(String cpf) {
        boolean isCpfAlreadyExists = this.userRepository.existsByCpf(cpf);

        if (isCpfAlreadyExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este CPF já foi registrado em nossa base de dados!"
            );
        }
    }

    private void verifyIfHasEmail(String email) {
        boolean isEmailAlreadyExists = this.userRepository.existsByEmail(email);

        if (isEmailAlreadyExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este e-mail já foi registrado em nossa base de dados!"
            );
        }
    }

    private void verifyIfHasId(Long id) {
        boolean isIdExists = this.userRepository.existsById(id);

        if (!isIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id informado é inválido!"
            );
        }
    }

    private UserEntity getUser(Long id) {
        return this.userRepository.getReferenceById(id);
    }

    public UserResponseDto save(UserRequestDto newUser) {
        this.verifyIfHasCpf(newUser.cpf());

        this.verifyIfHasEmail(newUser.email());

        UserEntity user = UserMapper.map(newUser);

        return new UserResponseDto(this.userRepository.save(user));
    }

    public UserResponseDto update(Long id, UserRequestPutDto userToUpdate) {
        UserEntity userFound = this.delete(id);

        UserEntity user = UserMapper.map(userToUpdate);

        user.setId(userFound.getId());
        user.setCpf(userFound.getCpf());
        user.setEmail(userFound.getEmail());

        return new UserResponseDto(this.userRepository.save(user));
    }

    public List<UserResponseDto> getAll() {
        return this.userRepository
                .findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public UserResponseDto getOne(Long id) {
        this.verifyIfHasId(id);

        UserEntity user = this.getUser(id);

        return new UserResponseDto(user);
    }

    public UserEntity delete(Long id) {
        this.verifyIfHasId(id);

        UserEntity userFound = this.getUser(id);

        this.userRepository.deleteById(id);

        return userFound;
    }
}
