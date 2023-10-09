package com.senai.M3PFBackEnd.entities;

import com.senai.M3PFBackEnd.enums.MedicamentType;
import com.senai.M3PFBackEnd.enums.MedicamentUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Medicament")
@Table(name = "medicaments")
@Getter
@Setter
public class MedicamentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length = 100)
    private String name;

    @Column(nullable=false)
    private LocalDate date;

    @Column(nullable=false)
    private LocalTime time;

    @Column(nullable=false)
    @Enumerated(value = EnumType.STRING)
    private MedicamentType type;

    // @Column(nullable=false, precision = 19, scale = 2)
    @Column(nullable=false)
    private Double quantity;

    @Column(nullable=false)
    @Enumerated(value = EnumType.STRING)
    private MedicamentUnit unit;

    @Column(nullable=false, length = 1000)
    private String observations;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean status = true;
}
