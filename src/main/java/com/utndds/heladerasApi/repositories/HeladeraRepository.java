package com.utndds.heladerasApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utndds.heladerasApi.models.Heladera.Heladera;

@Repository
public interface HeladeraRepository extends JpaRepository<Heladera, Long> {
    // Puedes agregar métodos personalizados aquí si lo deseas
}