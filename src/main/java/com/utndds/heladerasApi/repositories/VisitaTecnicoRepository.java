package com.utndds.heladerasApi.repositories;

import com.utndds.heladerasApi.models.Heladera.Incidentes.VisitaTecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaTecnicoRepository extends JpaRepository<VisitaTecnico, Long> {
    // Puedes agregar métodos personalizados aquí si lo deseas
}