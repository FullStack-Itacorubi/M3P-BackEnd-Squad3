package com.senai.M3PFBackEnd.mappers;

import com.senai.M3PFBackEnd.dtos.patient.PatientRequestPostDto;
import com.senai.M3PFBackEnd.entities.PatientEntity;
import com.senai.M3PFBackEnd.enums.CivilStatus;
import com.senai.M3PFBackEnd.enums.Genre;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class PatientMapper {
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private PatientMapper() {}
    // TODO: ver como formatar a data de validade
    private static LocalDate formatStringToLocalDate(String date) {
        String[] arrayString = date.split("/");
        int year = Integer.parseInt("20" + arrayString[1]);
        int month = Integer.parseInt(arrayString[0]);

        // String dateString = localDate.format(dateFormat);
        // return LocalDate.parse(date, dateFormat);
        // return LocalDate.parse(dateString);
        return LocalDate
                .now()
                .withYear(year)
                .withMonth(month)
                .with(TemporalAdjusters.lastDayOfMonth());
    }

    public static PatientEntity map(PatientRequestPostDto source) {
        PatientEntity target = new PatientEntity();

        target.setFullName(source.fullName());
        target.setGenre(Genre.valueOf(source.genre()));
        target.setCpf(source.cpf());
        target.setPhone(source.phone());
        target.setEmail(source.email());
        target.setBirthday(source.birthday());
        target.setRg(source.rg());
        target.setCivilStatus(CivilStatus.valueOf(source.civilStatus()));
        target.setPlaceOfBirth(source.placeOfBirth());
        target.setEmergencyContact(source.emergencyContact());
        target.setAllergyList(source.allergyList());
        target.setSpecificCareList(source.specificCareList());
        target.setHealthInsurance(source.healthInsurance());
        target.setHealthInsuranceNumber(source.healthInsuranceNumber());
        // target.setHealthInsuranceValidity(LocalDate.parse(source.healthInsuranceValidity()));
        target.setHealthInsuranceValidity(formatStringToLocalDate(source.healthInsuranceValidity()));
        target.setAddress(AddressMapper.map(source.address()));

        return target;
    }
}
