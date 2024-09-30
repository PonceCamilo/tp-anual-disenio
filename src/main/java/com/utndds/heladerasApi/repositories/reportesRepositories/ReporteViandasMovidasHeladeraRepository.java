package com.utndds.heladerasApi.repositories.reportesRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Reportes.ViandasMovidasPorHeladera;

@Repository
public interface ReporteViandasMovidasHeladeraRepository extends JpaRepository<ViandasMovidasPorHeladera, Long> {
    // Puedes agregar métodos personalizados aquí si lo deseas
}