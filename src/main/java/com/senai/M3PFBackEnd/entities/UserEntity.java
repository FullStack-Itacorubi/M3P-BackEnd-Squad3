package com.senai.M3PFBackEnd.entities;

import com.senai.M3PFBackEnd.enums.Genre;
import com.senai.M3PFBackEnd.enums.UserType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends PersonEntity {
    @Column(nullable=false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserType type;

    public UserEntity(
            String fullName,
            Genre genre,
            String cpf,
            String phone,
            String email,
            // boolean status,
            String password,
            UserType userType
    ) {
        super(
                fullName,
                genre,
                cpf,
                phone,
                email
        );
        this.setPassword(password);
        this.setType(userType);
    }
}
