package com.senai.M3PFBackEnd.dtos.user;

import com.senai.M3PFBackEnd.entities.UserEntity;

public record UserResponseDto(
        Long id,

        String fullName,

        String genre,

        String cpf,

        String phone,

        String email,

        String type,

        Boolean status
) {
    public UserResponseDto(UserEntity userEntity) {
        this(
                userEntity.getId(),
                userEntity.getFullName(),
                userEntity.getGenre().getType(),
                userEntity.getCpf(),
                userEntity.getPhone(),
                userEntity.getEmail(),
                userEntity.getType().getType(),
                userEntity.getStatus()
        );
    }

}
