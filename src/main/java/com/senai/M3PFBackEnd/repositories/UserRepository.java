package com.senai.M3PFBackEnd.repositories;

import com.senai.M3PFBackEnd.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsById(UUID id);

    UserEntity getReferenceById(UUID id);

    void deleteById(UUID id);
}
