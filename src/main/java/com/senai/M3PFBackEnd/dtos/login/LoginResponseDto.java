package com.senai.M3PFBackEnd.dtos.login;

import com.senai.M3PFBackEnd.entities.UserEntity;

public record LoginResponseDto(
        Long id,
        String fullName,
        String email,
        String type
) {

    public LoginResponseDto(UserEntity user) {
        this(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getType().getType()
        );
    }
}
