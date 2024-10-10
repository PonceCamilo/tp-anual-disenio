package com.utndds.heladerasApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Tarjetas.Apertura;

@Repository
public interface AperturaRepository extends JpaRepository<Apertura, Long> {
}