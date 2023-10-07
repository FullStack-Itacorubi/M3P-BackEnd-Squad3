package com.senai.M3PFBackEnd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MedicamentUnit {
    MG("mg"),
    MCG("mcg"),
    G("g"),
    ML("ml"),
    PERCENTAGE("%");

    private final String medicamentUnity;
}
