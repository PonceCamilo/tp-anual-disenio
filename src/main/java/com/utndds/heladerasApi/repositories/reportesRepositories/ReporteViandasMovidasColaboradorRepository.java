package com.utndds.heladerasApi.repositories.reportesRepositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utndds.heladerasApi.models.Reportes.ViandasMovidasPorColaborador;

@Repository
public interface ReporteViandasMovidasColaboradorRepository extends JpaRepository<ViandasMovidasPorColaborador, Long> {

    List<ViandasMovidasPorColaborador> findByFechaGeneracionAfter(LocalDate inicioUltimaSemana);
    // Puedes agregar métodos personalizados aquí si lo deseas
}