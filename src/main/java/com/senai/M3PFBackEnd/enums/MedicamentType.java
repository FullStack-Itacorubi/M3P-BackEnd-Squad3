package com.senai.M3PFBackEnd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MedicamentType {
    CAPSULE("Cápsula"),
    PILL("Comprimido"),
    LIQUID("Líquido"),
    CREAM("Creme"),
    GEL("Gel"),
    INHALATION("Inalação"),
    INJECTION("Injeção"),
    SPRAY("Spray");

    private final String type;
}
