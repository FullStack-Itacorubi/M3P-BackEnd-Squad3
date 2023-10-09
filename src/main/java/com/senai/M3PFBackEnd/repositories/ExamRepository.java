package com.senai.M3PFBackEnd.repositories;

import com.senai.M3PFBackEnd.entities.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {

}
