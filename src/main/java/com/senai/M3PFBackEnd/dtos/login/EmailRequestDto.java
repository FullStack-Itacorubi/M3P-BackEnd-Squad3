package com.senai.M3PFBackEnd.dtos.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequestDto(
        @NotBlank(message = "O campo e-mail é obrigatório!")
        @Email(message = "Email inválido!")
        String email
) {
}
