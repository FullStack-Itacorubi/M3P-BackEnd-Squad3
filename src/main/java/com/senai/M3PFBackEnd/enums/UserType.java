package com.senai.M3PFBackEnd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO: trabalhar com os valores
@Getter
@AllArgsConstructor
public enum UserType {
    DOCTOR("Médico"),
    ADMINISTRATOR("Administrador"),
    NURSE("Enfermeiro");

    private final String type;
}
