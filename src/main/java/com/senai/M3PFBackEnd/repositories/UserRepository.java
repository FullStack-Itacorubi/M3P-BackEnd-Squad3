package com.senai.M3PFBackEnd.repositories;

import com.senai.M3PFBackEnd.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    UserEntity getReferenceById(Long id);

    void deleteById(Long id);

    Optional<UserEntity> getByEmail(String email);

    @Query("from User u where lower(u.fullName) like %:filter% or lower(u.email) like %:filter% or u.phone like %:filter% or u.cpf like %:filter%")
    List<UserEntity> findAllWithFilter(String filter);
}
