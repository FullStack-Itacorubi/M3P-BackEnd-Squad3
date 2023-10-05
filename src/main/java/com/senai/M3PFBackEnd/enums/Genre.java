package com.senai.M3PFBackEnd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO: fazer enum Genre
@Getter
@AllArgsConstructor
public enum Genre {
    CISGENDER("Cisgênero"),
    TRANSGENDER("Transgênero"),
    NONBINARY("Não-binário");

    private final String type;

    /*Genre(String type) {
        this.type = type;
    }*/
}
