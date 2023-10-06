package com.senai.M3PFBackEnd.mappers;


import com.senai.M3PFBackEnd.dtos.QueryRequestDto;
import com.senai.M3PFBackEnd.dtos.QueryRequestPutDto;
import com.senai.M3PFBackEnd.entities.QueryEntity;

public class QueryMapper {
    private QueryMapper() {
    }

    public static QueryEntity map(QueryRequestDto source) {
        QueryEntity target = new QueryEntity();

        target.setReasonForConsultation(source.reasonForConsultation());
        target.setConsultationDate(source.consultationDate());
        target.setConsultationTime(source.consultationTime());
        target.setProblemDescription(source.problemDescription());
        target.setPrescriptionMedication(source.prescriptionMedication());
        target.setDosageAndRecautions(source.dosageAndRecautions());

        return target;
    }

    public static QueryEntity map(QueryRequestPutDto source) {
        QueryEntity target = new QueryEntity();

        target.setReasonForConsultation(source.reasonForConsultation());
        target.setConsultationDate(source.consultationDate());
        target.setConsultationTime(source.consultationTime());
        target.setProblemDescription(source.problemDescription());
        target.setPrescriptionMedication(source.prescriptionMedication());
        target.setDosageAndRecautions(source.dosageAndRecautions());
        target.setSystemStatus(source.systemStatus());

        return target;
    }
}

