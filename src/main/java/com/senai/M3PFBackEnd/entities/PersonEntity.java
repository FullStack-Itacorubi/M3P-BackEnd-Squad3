package com.senai.M3PFBackEnd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable=false)
    private String fullName;

    @Column(nullable=false)
    private String genre;

    @Column(nullable=false, unique = true)
    private String cpf;

    @Column(nullable=false)
    private String phone;

    @Column(nullable=false, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "boolean default true")
    // @Column(columnDefinition = "boolean default true")
    // @Column(nullable=false)
    private Boolean status = true;
}
