package com.senai.M3PFBackEnd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CivilStatus {
    SINGLE("Solteiro(a)"),
    MARRIED("Casado(a)"),
    SEPARATED("Separado(a)"),
    DIVORCED("Divorciado(a)"),
    WIDOWER("Viúvo(a)");

    private final String type;
}
