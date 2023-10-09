package com.senai.M3PFBackEnd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DietType {

        LOW_CARB("Low Carb"),
        DASH("Dash"),
        PALEOLITHIC("Paleolítica"),
        KETOGENIC("Cetogênica"),
        DUKAN("Dukan"),
        MEDITERRANEAN("Mediterrânea"),
        OTHER("Outra");

        private final String type;

}
