package com.senai.M3PFBackEnd.repositories;

import com.senai.M3PFBackEnd.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    UserEntity getReferenceById(Long id);

    void deleteById(Long id);
}
