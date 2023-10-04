package com.example.demodesafio03.dtos.User;

import com.example.demodesafio03.enums.UserType;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record UserRequestDto(
        @NotBlank(message = "O campo nome completo é obrigatório!")
        String fullName,

        @NotBlank(message = "O campo gênero é obrigatório!")
        String genre,

        @NotBlank(message = "O campo CPF é obrigatório!")
        @CPF(message = "O campo CPF é inválido!")
        String cpf,

        @NotBlank(message = "O campo telefone é obrigatório!")
        // @Pattern(regexp = "\d{8,12}")
        // @Pattern(regexp = "(\([0-9]{2}\)[0-9]{5}-[0-9]{4})|(\([0-9]{2}\)[0-9]{4}-[0-9]{4})")
        String phone,

        @NotBlank(message = "O campo CPF é obrigatório!")
        @Email(message = "O campo e-mail é inválido!")
        String email,

        @NotBlank(message = "O campo senha é obrigatório!")
        @Size(min = 6, message = "O campo senha deve ter no mínimo 6 caracteres!")
        String password,

        @NotNull(message = "O campo tipo é obrigatório!")
        UserType type
) {
}
