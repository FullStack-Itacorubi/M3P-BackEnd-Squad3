package com.senai.M3PFBackEnd.dtos.patient;

import com.senai.M3PFBackEnd.dtos.address.AddressRequestPostDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PatientRequestPostDto(
        @NotBlank(message = "O campo nome completo é obrigatório!")
        @Size(min = 8, message = "O campo nome deve ter no mínimo 8 caracteres")
        String fullName,

        @NotBlank(message = "O campo gênero é obrigatório!")
        @Pattern(
                regexp = "CISGENDER|TRANSGENDER|NONBINARY",
                message = "O tipo de ser: CISGENDER, TRANSGENDER ou NONBINARY"
        )
        String genre,

        @NotBlank(message = "O campo CPF é obrigatório!")
        @CPF(message = "O campo CPF é inválido!")
        String cpf,

        @NotBlank(message = "O campo telefone é obrigatório!")
        // @Pattern(regexp = "\d{8,12}")
        @Pattern(
                regexp = "(\\([0-9]{2}\\) [0-9] [0-9]{4}-[0-9]{4})",
                message = "O campo telefone deve ter o seguinte formato: (00) 9 8765-4321"
        )
        String phone,

        @NotBlank(message = "O campo CPF é obrigatório!")
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

        @NotBlank(message = "O campo CPF é obrigatório!")
        @Size(max = 20)
        String rg,

        @NotBlank(message = "O campo estado cívil é obrigatório!")
        @Pattern(
                regexp = "SINGLE|MARRIED|SEPARATED|DIVORCED|WIDOWER",
                message = "O tipo de ser: SINGLE, MARRIED, SEPARATED, DIVORCED ou WIDOWER"
        )
        String civilStatus,

        @NotBlank(message = "O campo naturalidade é obrigatório!")
        @Size(min = 8, message = "O campo naturalidade deve ter no mínimo 8 caracteres")
        String placeOfBirth,

        @NotBlank(message = "O campo contato de emergência é obrigatório!")
        @Pattern(
                regexp = "(\\([0-9]{2}\\) [0-9] [0-9]{4}-[0-9]{4})",
                message = "O campo telefone deve ter o seguinte formato: (00) 9 8765-4321"
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

        @NotNull(message = "O campo endereço é obrigatório!")
        @Valid
        AddressRequestPostDto address
) {
}
