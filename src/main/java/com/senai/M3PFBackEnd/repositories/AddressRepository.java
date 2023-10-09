package com.senai.M3PFBackEnd.repositories;

import com.senai.M3PFBackEnd.entities.AddressEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
