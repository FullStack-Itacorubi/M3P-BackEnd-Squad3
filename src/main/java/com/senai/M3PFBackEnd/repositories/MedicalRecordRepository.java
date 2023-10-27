package com.senai.M3PFBackEnd.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.M3PFBackEnd.entities.MedicalRecordEntity;

@Repository
public interface MedicalRecordRepository extends JpaRepository <MedicalRecordEntity, Long> {
    List<MedicalRecordEntity> findAllByPatientIdAndPatientFullNameContainingIgnoringCase(Long id, String name);

    List<MedicalRecordEntity> findAllByPatientId(Long id);

    List<MedicalRecordEntity> findAllByPatientFullNameContainingIgnoringCase(String name);

    boolean existsByPatientId(Long id);
}
