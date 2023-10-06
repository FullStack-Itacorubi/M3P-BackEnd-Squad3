package com.senai.M3PFBackEnd.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.senai.M3PFBackEnd.enums.exercise.ExerciseType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Exercise")
@Table(name = "exercises")
@Getter
@Setter
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private ExerciseType type;

    @Column(nullable = false)
    private Integer weeklyAmmount;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean status;
}
