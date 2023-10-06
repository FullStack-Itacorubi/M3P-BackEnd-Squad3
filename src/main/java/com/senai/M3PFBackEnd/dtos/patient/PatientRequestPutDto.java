package com.senai.M3PFBackEnd.dtos.patient;

import com.senai.M3PFBackEnd.dtos.address.AddressRequestPostDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PatientRequestPutDto(
        @NotBlank(message = "O campo nome completo é obrigatório!")
        @Size(
                min = 8,
                max = 64,
                message = "O campo nome completo deve ter no mínimo 8 caracteres"
        )
        String fullName,

        @NotBlank(message = "O campo gênero é obrigatório!")
        @Pattern(
                regexp = "CISGENDER|TRANSGENDER|NONBINARY",
                message = "O campo gênero deve ser: CISGENDER, TRANSGENDER ou NONBINARY"
        )
        String genre,

        @NotBlank(message = "O campo telefone é obrigatório!")
        @Pattern(
                regexp = "(\\([0-9]{2}\\) [0-9] [0-9]{4}-[0-9]{4})",
                message = "O campo telefone deve ter o seguinte formato: (00) 9 8765-4321"
        )
        String phone,

        @NotBlank(message = "O campo e-mail é obrigatório!")
        @Email(message = "O campo e-mail é inválido!")
        String email,

        @NotNull(message = "O campo data de nascimento é obrigatório!")
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "dd/MM/yyyy",
                locale = "pt-BR",
                timezone = "Brazil/East"
        )
        LocalDate birthday,

        @NotBlank(message = "O campo estado cívil é obrigatório!")
        @Pattern(
                regexp = "SINGLE|MARRIED|SEPARATED|DIVORCED|WIDOWER",
                message = "O O campo estado cívil deve ser: SINGLE, MARRIED, SEPARATED, DIVORCED ou WIDOWER"
        )
        String civilStatus,

        @NotBlank(message = "O campo naturalidade é obrigatório!")
        @Size(min = 8, message = "O campo naturalidade deve ter no mínimo 8 caracteres")
        String placeOfBirth,

        @NotBlank(message = "O campo contato de emergência é obrigatório!")
        @Pattern(
                regexp = "(\\([0-9]{2}\\) [0-9] [0-9]{4}-[0-9]{4})",
                message = "O campo contato de emergência deve ter o seguinte formato: (00) 9 8765-4321"
        )
        String emergencyContact,

        String allergyList,

        String specificCareList,

        String healthInsurance,

        String healthInsuranceNumber,

        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "MM/yyyy",
                locale = "pt-BR",
                timezone = "Brazil/East"
        )
        String healthInsuranceValidity,

        @Valid
        AddressRequestPostDto address,

        @NotNull(message = "O campo status é obrigatório!")
        Boolean status
) {
}
