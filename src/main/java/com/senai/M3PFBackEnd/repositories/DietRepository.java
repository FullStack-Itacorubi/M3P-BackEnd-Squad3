package com.senai.M3PFBackEnd.repositories;

import com.senai.M3PFBackEnd.entities.DietEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietRepository extends JpaRepository<DietEntity, Long> {

}
