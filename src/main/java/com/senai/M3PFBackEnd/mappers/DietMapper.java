package com.senai.M3PFBackEnd.mappers;

import com.senai.M3PFBackEnd.dtos.Diet.DietRequestDto;
import com.senai.M3PFBackEnd.dtos.Diet.DietRequestPutDto;
import com.senai.M3PFBackEnd.entities.DietEntity;
import com.senai.M3PFBackEnd.enums.DietType;

public class DietMapper {
    private DietMapper() {
    }

    public static DietEntity map(DietRequestDto source) {
        DietEntity target = new DietEntity();

        target.setDietName(source.dietName());
        target.setDietDate(source.dietDate());
        target.setDietTime(source.dietTime());
        target.setType(DietType.valueOf(source.type()));
        target.setDescription(source.description());

        return target;
    }

    public static DietEntity map(DietRequestPutDto source) {
        DietEntity target = new DietEntity();

        target.setDietName(source.dietName());
        target.setType(DietType.valueOf(source.type()));
        target.setDescription(source.description());
        target.setStatus(source.status());

        return target;
    }

}
