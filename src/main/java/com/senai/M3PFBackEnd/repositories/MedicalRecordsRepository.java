package com.senai.M3PFBackEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senai.M3PFBackEnd.entities.MedicalRecordsEntity;

public interface MedicalRecordsRepository extends JpaRepository <MedicalRecordsEntity, Long> {
    
}
