package com.senai.M3PFBackEnd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genre {
    CISGENDER("Cisgênero"),
    TRANSGENDER("Transgênero"),
    NONBINARY("Não-binário");

    private final String type;
}
