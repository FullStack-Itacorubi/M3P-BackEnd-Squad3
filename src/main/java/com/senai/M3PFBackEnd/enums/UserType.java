package com.senai.M3PFBackEnd.enums;

public enum UserType {
    DOCTOR("Médico"),
    ADMINISTRATOR("Administrador"),
    NURSE("Enfermeiro");

    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
