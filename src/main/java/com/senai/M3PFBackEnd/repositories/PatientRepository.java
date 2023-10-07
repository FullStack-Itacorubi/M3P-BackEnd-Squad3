package com.senai.M3PFBackEnd.repositories;

import com.senai.M3PFBackEnd.entities.PatientEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
