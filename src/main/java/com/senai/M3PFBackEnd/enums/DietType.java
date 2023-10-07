package com.senai.M3PFBackEnd.enums;

public enum DietType {

        LOW_CARB("Low Carb"),
        DASH("Dash"),
        PALEOLITHIC("Paleolítica"),
        KETOGENIC("Cetogênica"),
        DUKAN("Dukan"),
        MEDITERRANEAN("Mediterrânea"),
        OTHER("Outra");

        private final String type;

        DietType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

}
