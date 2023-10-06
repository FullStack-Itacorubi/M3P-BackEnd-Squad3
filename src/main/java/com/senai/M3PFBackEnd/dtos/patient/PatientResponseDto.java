package com.senai.M3PFBackEnd.dtos.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senai.M3PFBackEnd.dtos.address.AddressResponseDto;
import com.senai.M3PFBackEnd.entities.PatientEntity;

import java.time.LocalDate;

public record PatientResponseDto(
        Long id,

        String fullName,

        String genre,

        String cpf,

        String phone,

        String email,

        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "dd/MM/yyyy",
                locale = "pt-BR",
                timezone = "Brazil/East"
        )
        LocalDate birthday,

        String rg,

        String civilStatus,

        String placeOfBirth,

        String emergencyContact,

        String allergyList,

        String specificCareList,

        String healthInsurance,

        String healthInsuranceNumber,

        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "dd/MM/yyyy",
                locale = "pt-BR",
                timezone = "Brazil/East"
        )
        LocalDate healthInsuranceValidity,

        AddressResponseDto address
) {
    public PatientResponseDto(PatientEntity patient) {
        this(
                patient.getId(),
                patient.getFullName(),
                patient.getGenre().getType(),
                patient.getCpf(),
                patient.getPhone(),
                patient.getEmail(),
                patient.getBirthday(),
                patient.getRg(),
                patient.getCivilStatus().getType(),
                patient.getPlaceOfBirth(),
                patient.getEmergencyContact(),
                patient.getAllergyList(),
                patient.getSpecificCareList(),
                patient.getHealthInsurance(),
                patient.getHealthInsuranceNumber(),
                patient.getHealthInsuranceValidity(),
                new AddressResponseDto(patient.getAddress())
        );
    }
}
