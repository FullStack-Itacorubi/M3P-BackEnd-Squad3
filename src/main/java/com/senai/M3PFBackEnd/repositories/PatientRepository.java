package com.senai.M3PFBackEnd.repositories;

import com.senai.M3PFBackEnd.entities.PatientEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    @Query("from Patient p where lower(p.fullName) like %:filter% or lower(p.email) like %:filter% or p.phone like %:filter% or p.cpf like %:filter%")
    List<PatientEntity> findAllWithFilter(String filter);
}
