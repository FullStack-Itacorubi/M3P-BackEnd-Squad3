package com.senai.M3PFBackEnd.mappers;

import com.senai.M3PFBackEnd.dtos.exam.ExamRequestPostDto;
import com.senai.M3PFBackEnd.entities.ExamEntity;

public class ExamMapper {
    private ExamMapper(){ }

    public static ExamEntity map(ExamRequestPostDto source){
        ExamEntity target = new ExamEntity();

        target.setExamName(source.examName());
        target.setExamDate(source.examDate());
        target.setExamHour(source.examHour());
        target.setExamType(source.examType());
        target.setDocumentUrl(source.documentUrl());
        target.setLaboratory(source.laboratory());
        target.setResults(source.results());

        return target;
    }
}
