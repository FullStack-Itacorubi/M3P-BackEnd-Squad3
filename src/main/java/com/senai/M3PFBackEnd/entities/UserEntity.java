package com.senai.M3PFBackEnd.entities;

import com.senai.M3PFBackEnd.enums.UserType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends PersonEntity {
    @Column(nullable=false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserType type;
}
