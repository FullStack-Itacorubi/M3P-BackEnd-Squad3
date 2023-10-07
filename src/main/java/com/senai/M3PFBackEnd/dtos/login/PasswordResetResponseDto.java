package com.senai.M3PFBackEnd.dtos.login;

import com.senai.M3PFBackEnd.entities.UserEntity;

public record PasswordResetResponseDto(
        Long id,
        String email
) {
    public PasswordResetResponseDto(
            UserEntity user
    ) {
        this(
                user.getId(),
                user.getEmail()
        );
    }
}
