package com.senai.M3PFBackEnd.mappers;

import com.senai.M3PFBackEnd.dtos.medicament.MedicamentRequestPostDto;
import com.senai.M3PFBackEnd.entities.MedicamentEntity;
import com.senai.M3PFBackEnd.enums.MedicamentType;
import com.senai.M3PFBackEnd.enums.MedicamentUnit;

public class MedicamentMapper {
    private MedicamentMapper() {
    }

    public static MedicamentEntity map(MedicamentRequestPostDto source) {
        MedicamentEntity target = new MedicamentEntity();

        target.setName(source.name());
        target.setDate(source.date());
        target.setTime(source.time());
        target.setType(MedicamentType.valueOf(source.type()));
        target.setQuantity(source.quantity());
        target.setUnit(MedicamentUnit.valueOf(source.unit()));
        target.setObservations(source.observations());

        return target;
    }
}
