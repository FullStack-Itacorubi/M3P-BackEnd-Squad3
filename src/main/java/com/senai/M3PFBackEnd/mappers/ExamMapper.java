package com.senai.M3PFBackEnd.mappers;

import com.senai.M3PFBackEnd.dtos.exam.ExamRequestDto;
import com.senai.M3PFBackEnd.entities.ExamEntity;

public class ExamMapper {
    private ExamMapper(){ }

    public static ExamEntity map(ExamRequestDto source){
        ExamEntity target = new ExamEntity();

        target.getExamName();
        target.getExamDate();
        target.getExamHour();
        target.getExamType();
        target.getLaboratory();
        target.getResults();

        return target;
    }
}
