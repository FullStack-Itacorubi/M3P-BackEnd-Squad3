package com.senai.M3PFBackEnd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Exam")
@Table(name = "exams")
@Getter
@Setter
public class ExamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String examName;

    @Column(nullable = false)
    private LocalDate examDate;

    @Column(nullable = false)
    private LocalTime examHour;

    @Column(nullable = false)
    private String examType;

    @Column(nullable = false)
    private String laboratory;

    @Column(nullable = false)
    private String documentUrl;

    @Column(nullable = false)
    private String results;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean status = true;
}
