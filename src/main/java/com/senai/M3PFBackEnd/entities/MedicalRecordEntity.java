package com.senai.M3PFBackEnd.entities;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "MedicalRecord")
@Table(name = "medical_records")
@Getter
@Setter
@NoArgsConstructor
public class MedicalRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private PatientEntity patient;
    
    @OneToMany
    private List<ExamEntity> exams;

    @OneToMany
    private List<ExerciseEntity> exercises;

    // TODO: adicionar relacionamentos de consulta e dieta

    public MedicalRecordEntity(PatientEntity patient) {
        this.patient = patient;
    }

    @PreRemove
    public void checkIfHasAnyAssociationBeforeRemoval() {
        if(!this.exams.isEmpty())
            throw new RuntimeException("Não é possível excluir paciente, pois existe pelo menos um exame associado a ele.");

        if(!this.exercises.isEmpty())
            throw new RuntimeException("Não é possível excluir paciente, pois existe pelo menos um exercício associado a ele.");

        // TODO: adicionar exceções para consulta e dieta
    }
}
