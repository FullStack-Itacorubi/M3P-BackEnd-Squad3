package com.senai.M3PFBackEnd.dtos.user;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record UserRequestPostDto(
        @NotBlank(message = "O campo nome completo é obrigatório!")
        @Size(min = 8, message = "O campo nome deve ter no mínimo 8 caracteres")
        String fullName,

        @NotBlank(message = "O campo gênero é obrigatório!")
        @Pattern(
                regexp = "CISGENDER|TRANSGENDER|NONBINARY",
                message = "O tipo deve ser: CISGENDER, TRANSGENDER ou NONBINARY"
        )
        String genre,

        @NotBlank(message = "O campo CPF é obrigatório!")
        @CPF(message = "O campo CPF é inválido!")
        String cpf,

        @NotBlank(message = "O campo telefone é obrigatório!")
        @Pattern(
                regexp = "(\\([0-9]{2}\\) [0-9] [0-9]{4}-[0-9]{4})",
                message = "O campo telefone deve ter o seguinte formato: (00) 9 8765-4321"
        )
        String phone,

        @NotBlank(message = "O campo CPF é obrigatório!")
        @Email(message = "O campo e-mail é inválido!")
        String email,

        @NotBlank(message = "O campo senha é obrigatório!")
        @Size(min = 6, message = "O campo senha deve ter no mínimo 6 caracteres!")
        String password,

        @NotBlank(message = "O campo tipo é obrigatório!")
        @Pattern(
                regexp = "ADMINISTRATOR|DOCTOR|NURSE",
                message = "O tipo deve ser: ADMINISTRATOR, DOCTOR ou NURSE"
        )
        String type
) {
}
