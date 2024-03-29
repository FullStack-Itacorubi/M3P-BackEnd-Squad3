package com.senai.M3PFBackEnd.services;

import com.senai.M3PFBackEnd.dtos.login.LoginRequestDto;
import com.senai.M3PFBackEnd.dtos.login.LoginResponseDto;
import com.senai.M3PFBackEnd.dtos.login.PasswordResetRequestDto;
import com.senai.M3PFBackEnd.dtos.login.PasswordResetResponseDto;
import com.senai.M3PFBackEnd.dtos.user.UserRequestPostDto;
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

    @Autowired
    private LogsService logsService;

    private void verifyIfHasCpf(String cpf) {
        boolean isCpfAlreadyExists = this.userRepository.existsByCpf(cpf);

        if (isCpfAlreadyExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este CPF já foi registrado em nossa base de dados!");
        }
    }

    private void verifyIfHasEmail(String email) {
        boolean isEmailAlreadyExists = this.userRepository.existsByEmail(email);

        if (isEmailAlreadyExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este e-mail já foi registrado em nossa base de dados!");
        }
    }

    private void verifyIfHasId(Long id) {
        boolean isIdExists = this.userRepository.existsById(id);

        if (!isIdExists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O id informado é inválido!");
        }
    }

    private UserEntity getUser(Long id) {
        return this.userRepository.getReferenceById(id);
    }

    public UserResponseDto save(UserRequestPostDto newUser, Long userId) {
        this.verifyIfHasCpf(newUser.cpf());

        this.verifyIfHasEmail(newUser.email());

        UserEntity user = UserMapper.map(newUser);
        user = this.userRepository.save(user);

        logsService.saveLog("O usuário de id " + userId + " criou um novo usuário: " + user.getFullName() + "("
                + user.getId() + ") do tipo " + user.getType());

        return new UserResponseDto(user);
    }

    public UserResponseDto update(Long id, UserRequestPutDto userToUpdate, Long userId) {
        this.verifyIfHasId(id);
        UserEntity userFound = getUser(id);

        UserEntity user = UserMapper.map(userToUpdate);

        user.setId(userFound.getId());
        user.setCpf(userFound.getCpf());
        user.setEmail(userFound.getEmail());
        user.setPassword(userFound.getPassword());
        user = this.userRepository.save(user);

        logsService.saveLog("O usuário de id " + userId + " atualizou o usuário: " + user.getFullName() + "("
                + user.getId() + ")");

        return new UserResponseDto(user);
    }

    public List<UserResponseDto> getAll() {
        return this.userRepository
                .findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public List<UserResponseDto> getAll(String filter) {
        return this.userRepository
                .findAllWithFilter(filter)
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public UserResponseDto getOne(Long id) {
        this.verifyIfHasId(id);

        UserEntity user = this.getUser(id);

        return new UserResponseDto(user);
    }

    public void delete(Long id, Long userId) {
        this.verifyIfHasId(id);

        this.userRepository.deleteById(id);

        this.logsService.saveLog("O usuário de id " + userId + " excluiu o usuário de id " + id);
    }

    public LoginResponseDto login(LoginRequestDto newLogin) {
        UserEntity user = userRepository.getByEmail(newLogin.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha ou e-mail inválido!"));
        if (!passwordMatches(user.getPassword(), newLogin.password()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha ou e-mail inválido!");

        this.logsService.saveLog("O usuário " + user.getFullName() + "("
                + user.getId() + ") se conectou ao sistema.");

        return new LoginResponseDto(user);
    }

    private boolean passwordMatches(String password, String passedPassword) {
        return password.equals(passedPassword);
    }

    public PasswordResetResponseDto updatePassword(PasswordResetRequestDto newPassword) {
        this.verifyIfHasId(newPassword.id());
        UserEntity user = getUser(newPassword.id());
        if (!emailMatches(user.getEmail(), newPassword.email()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email inválido!");
        user.setPassword(newPassword.password());
        user = userRepository.save(user);

        this.logsService.saveLog("O usuário " + user.getFullName() + "("
                + user.getId() + ") resetou sua senha.");

        return new PasswordResetResponseDto(user);
    }

    private boolean emailMatches(String email, String passedEmail) {
        return email.equals(passedEmail);
    }

    public PasswordResetResponseDto getByEmail(String email) {
        UserEntity user = userRepository.getByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));

        return new PasswordResetResponseDto(user);
    }
}
