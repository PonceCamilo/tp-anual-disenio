package com.utndds.heladerasApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utndds.heladerasApi.models.Heladera.Vianda;

public interface ViandaRepository extends JpaRepository<Vianda, Long> {
}