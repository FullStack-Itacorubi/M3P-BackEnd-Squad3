package com.senai.M3PFBackEnd.dtos.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
        @NotBlank(message = "O campo e-mail é obrigatório!")
        @Email(message = "Email inválido!")
        String email,

        @NotBlank(message = "O campo senha é obrigatório!")
        @Size(min = 6, message = "A senha precisa ter no mínimo 6 caracteres")
        String password
) {
}
