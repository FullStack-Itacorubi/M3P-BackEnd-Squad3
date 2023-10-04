package com.senai.M3PFBackEnd.dtos.User;

import com.senai.M3PFBackEnd.entities.UserEntity;
import com.senai.M3PFBackEnd.enums.UserType;

import java.util.UUID;

public record UserResponseDto(
        UUID id,

        String fullName,

        String genre,

        String cpf,

        String phone,

        String email,

        UserType type
) {
    public UserResponseDto(UserEntity userEntity) {
        this(
                userEntity.getId(),
                userEntity.getFullName(),
                userEntity.getGenre(),
                userEntity.getCpf(),
                userEntity.getPhone(),
                userEntity.getEmail(),
                userEntity.getType()
        );
    }
}
