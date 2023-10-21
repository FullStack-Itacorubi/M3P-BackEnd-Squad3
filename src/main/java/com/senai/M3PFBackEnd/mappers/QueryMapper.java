package com.senai.M3PFBackEnd.mappers;

import com.senai.M3PFBackEnd.dtos.query.QueryRequestDto;
import com.senai.M3PFBackEnd.dtos.query.QueryRequestPutDto;
import com.senai.M3PFBackEnd.entities.QueryEntity;


public class QueryMapper {
    private QueryMapper() {}


    public static QueryEntity map(QueryRequestDto source) {
        QueryEntity target = new QueryEntity();

        target.setReasonForConsultation(source.reasonForConsultation());
        target.setConsultationDate(source.consultationDate());
        target.setConsultationTime(source.consultationTime());
        target.setProblemDescription(source.problemDescription());
        target.setMedicaments(
                source.medicaments() != null ?
                        source.medicaments() :
                        null
        );
        target.setDosageAndRecautions(source.dosageAndRecautions());

        return target;
    }

    public static QueryEntity map(QueryRequestPutDto source) {
        QueryEntity target = new QueryEntity();

        target.setReasonForConsultation(source.reasonForConsultation());
        target.setConsultationDate(source.consultationDate());
        target.setConsultationTime(source.consultationTime());
        target.setProblemDescription(source.problemDescription());
        target.setMedicaments(
                source.medicaments() != null ?
                        source.medicaments() :
                        null
        );
        target.setDosageAndRecautions(source.dosageAndRecautions());
        target.setStatus(source.status());


        return target;
    }
}

