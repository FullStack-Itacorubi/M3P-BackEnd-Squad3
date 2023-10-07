package com.senai.M3PFBackEnd.entities;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "MedicalRecord")
@Table(name = "medical_records")
@Getter
@Setter
public class MedicalRecordsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @Column(nullable = false)
    private PatientEntity patient;
    
    @OneToMany
    @Column(nullable = true)
    private List<ExamEntity> exams;

    @OneToMany
    @Column(nullable = true)
    private List<ExerciseEntity> exercises;

    // TODO: adicionar relacionamentos de consulta e dieta

    @PreRemove
    public void checkIfHasAnyAssociationBeforeRemoval() {
        if(!this.exams.isEmpty())
            throw new RuntimeException("Não é possível excluir paciente, pois existe pelo menos um exame associado a ele.");

        if(!this.exercises.isEmpty())
            throw new RuntimeException("Não é possível excluir paciente, pois existe pelo menos um exercício associado a ele.");

        // TODO: adicionar exceções para consulta e dieta
    }
}
