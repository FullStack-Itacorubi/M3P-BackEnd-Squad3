package com.senai.M3PFBackEnd.dtos.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressRequestPostDto(
        @NotBlank(message = "O campo CEP é obrigatório!")
        @Pattern(
                regexp = "[0-9]{5}-[0-9]{3}",
                message = "O campo CEP deve estar no seguinte formato: 01234-56"
        )
        String cep,

        @NotBlank(message = "O campo cidade é obrigatório!")
        String city,

        @NotBlank(message = "O campo estado é obrigatório!")
        String state,

        @NotBlank(message = "O campo logradouro é obrigatório!")
        String publicPlace,

        @NotBlank(message = "O campo número é obrigatório!")
        @Pattern(
                // regexp = "\\d",
                // regexp = "[0-9]",
                // regexp = "/^[0-9]*$/",
                // regexp = "/^\\d+$/",
                regexp = "^[0-9]+$",
                message = "O campo número deve contar apenas números"
        )
        String number,

        String complement,

        @NotBlank(message = "O campo bairro é obrigatório!")
        String neighborhood,

        String referencePoint
) {
}
