package com.senai.M3PFBackEnd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Address")
@Table(name = "addresses")
@Getter
@Setter
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String cep;

    @Column(nullable=false)
    private String city;

    @Column(nullable=false)
    private String state;

    @Column(nullable=false)
    private String publicPlace;

    @Column(nullable=false)
    private String number;

    private String complement;

    @Column(nullable=false)
    private String neighborhood;

    private String referencePoint;
}
