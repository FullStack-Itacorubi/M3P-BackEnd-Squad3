package com.senai.M3PFBackEnd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Query")
@Table(name = "queries")
@Getter
@Setter
public class QueryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String reasonForConsultation;

    @Column(nullable=false)
    private LocalDate consultationDate;

    @Column(nullable=false)
    private LocalTime consultationTime;

    @Column(nullable=false)
    private String problemDescription;

    //Atualizar e Vincular com entidade Medicamentos
    @Column(nullable=false)
    private String prescriptionMedication;

    @Column(nullable=false)
    private String dosageAndRecautions;

    @Column(nullable=false, columnDefinition = "boolean default true")
    private Boolean systemStatus = true;

}
