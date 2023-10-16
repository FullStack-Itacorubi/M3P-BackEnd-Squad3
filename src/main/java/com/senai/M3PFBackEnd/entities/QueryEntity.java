package com.senai.M3PFBackEnd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    @OneToMany()
    private List<MedicamentEntity> medicaments;

    @Column(nullable=false)
    private String dosageAndRecautions;

    @Column(nullable=false, columnDefinition = "boolean default true")
    private Boolean status = true;

}
