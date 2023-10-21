package com.senai.M3PFBackEnd.entities;

import com.senai.M3PFBackEnd.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Diet")
@Table(name = "diets")
@Getter
@Setter
public class DietEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String dietName;

    @Column(nullable=false)
    private LocalDate dietDate;

    @Column(nullable=false)
    private LocalTime dietTime;

    @Column(nullable=false)
    @Enumerated(value = EnumType.STRING)
    private DietType type;

    @Column(nullable=false)
    private String description;

    @Column(nullable=false, columnDefinition = "boolean default true")
    private Boolean status = true;
}
