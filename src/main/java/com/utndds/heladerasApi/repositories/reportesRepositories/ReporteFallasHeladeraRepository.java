package com.utndds.heladerasApi.repositories.reportesRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Reportes.FallasPorHeladera;

@Repository
public interface ReporteFallasHeladeraRepository extends JpaRepository<FallasPorHeladera, Long> {
    // Puedes agregar métodos personalizados aquí si lo deseas
}