package com.senai.M3PFBackEnd.repositories;

import com.senai.M3PFBackEnd.entities.QueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<QueryEntity, Long> {
    boolean existsById(Long id);

    QueryEntity getReferenceById(Long id);

    void deleteById(Long id);
}

