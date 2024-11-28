package com.utndds.heladerasApi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utndds.heladerasApi.models.Heladera.Heladera;

@Repository
public interface HeladeraRepository extends JpaRepository<Heladera, Long> {
    @SuppressWarnings("null")
    Optional<Heladera> findById(Long id);
}