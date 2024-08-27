package com.utndds.heladerasApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utndds.heladerasApi.models.ONG.ONG;

public interface ONGRepository extends JpaRepository<ONG, Long> {
    ONG findByNombre(String nombre);
}