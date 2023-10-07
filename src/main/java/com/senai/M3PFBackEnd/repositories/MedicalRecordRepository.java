package com.senai.M3PFBackEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senai.M3PFBackEnd.entities.MedicalRecordEntity;

@Repository
public interface MedicalRecordRepository extends JpaRepository <MedicalRecordEntity, Long> {
    
}
