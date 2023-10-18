package com.senai.M3PFBackEnd.entities;

import com.senai.M3PFBackEnd.enums.CivilStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "Patient")
@Table(name = "patients")
@Getter
@Setter
public class PatientEntity extends PersonEntity {
    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false, length = 20)
    private String rg;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CivilStatus civilStatus;

    @Column(nullable=false, length = 64)
    private String placeOfBirth;

    @Column(nullable=false)
    private String emergencyContact;

    private String allergyList;

    private String specificCareList;

    private String healthInsurance;

    private String healthInsuranceNumber;

    private LocalDate healthInsuranceValidity;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;
}
