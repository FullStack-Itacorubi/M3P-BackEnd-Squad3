package com.senai.M3PFBackEnd.repositories;

import com.senai.M3PFBackEnd.entities.MedicamentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentRepository extends JpaRepository<MedicamentEntity, Long> {
}
