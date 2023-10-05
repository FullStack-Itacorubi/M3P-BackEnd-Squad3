package com.senai.M3PFBackEnd.entities;

import com.senai.M3PFBackEnd.enums.Genre;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String fullName;

    @Column(nullable=false)
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Column(nullable=false, unique = true)
    private String cpf;

    @Column(nullable=false)
    private String phone;

    @Column(nullable=false, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean status = true;
}
