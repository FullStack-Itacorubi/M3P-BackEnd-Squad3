package com.senai.M3PFBackEnd.mappers;

import com.senai.M3PFBackEnd.dtos.exam.ExamRequestPostDto;
import com.senai.M3PFBackEnd.dtos.exam.ExamRequestPutDto;
import com.senai.M3PFBackEnd.dtos.user.UserRequestPutDto;
import com.senai.M3PFBackEnd.entities.ExamEntity;
import com.senai.M3PFBackEnd.entities.UserEntity;
import com.senai.M3PFBackEnd.enums.Genre;
import com.senai.M3PFBackEnd.enums.UserType;

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

    public static ExamEntity map(ExamRequestPutDto source) {
        ExamEntity target = new ExamEntity();

        target.setExamName(source.examName());
        target.setExamDate(source.examDate());
        target.setExamHour(source.examHour());
        target.setExamType(source.examType());
        target.setLaboratory(source.laboratory());
        target.setDocumentUrl(source.documentUrl());
        target.setResults(source.results());
        target.setStatus(source.status());

        return target;
    }
}
