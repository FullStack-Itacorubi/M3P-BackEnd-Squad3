package com.senai.M3PFBackEnd.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senai.M3PFBackEnd.entities.MedicalRecordEntity;

@Repository
public interface MedicalRecordRepository extends JpaRepository <MedicalRecordEntity, Long> {
    public List<MedicalRecordEntity> findAllByPatientIdAndPatientFullNameContaining(Long id, String name);

    public List<MedicalRecordEntity> findAllByPatientId(Long id);

    public List<MedicalRecordEntity> findAllByPatientFullNameContaining(String name);
}
