package com.senai.M3PFBackEnd.dtos.User;

import com.senai.M3PFBackEnd.enums.UserType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestPutDto(
        @NotBlank(message = "O campo nome completo é obrigatório!")
        String fullName,

        @NotBlank(message = "O campo gênero é obrigatório!")
        String genre,

        @NotBlank(message = "O campo telefone é obrigatório!")
        // @Pattern(regexp = "\d{8,12}")
        // @Pattern(regexp = "(\([0-9]{2}\)[0-9]{5}-[0-9]{4})|(\([0-9]{2}\)[0-9]{4}-[0-9]{4})")
        String phone,

        @NotBlank(message = "O campo senha é obrigatório!")
        @Size(min = 6, message = "O campo senha deve ter no mínimo 6 caracteres!")
        String password,

        @NotNull(message = "O campo tipo é obrigatório!")
        UserType type
) {
}
