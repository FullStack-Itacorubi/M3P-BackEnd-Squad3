package com.senai.M3PFBackEnd.dtos.User;

import com.senai.M3PFBackEnd.entities.UserEntity;

import java.util.UUID;

public record UserResponseDto(
        UUID id,

        String fullName,

        String genre,

        String cpf,

        String phone,

        String email,

        String type
) {
    public UserResponseDto(UserEntity userEntity) {
        this(
                userEntity.getId(),
                userEntity.getFullName(),
                userEntity.getGenre().getType(),
                userEntity.getCpf(),
                userEntity.getPhone(),
                userEntity.getEmail(),
                userEntity.getType().getType()
        );
    }
}
