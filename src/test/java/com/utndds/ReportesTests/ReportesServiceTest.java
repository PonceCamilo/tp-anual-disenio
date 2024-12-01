package com.utndds.ReportesTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.utndds.heladerasApi.models.Reportes.FallasPorHeladera;
import com.utndds.heladerasApi.models.Reportes.ViandasMovidasPorColaborador;
import com.utndds.heladerasApi.models.Reportes.ViandasMovidasPorHeladera;
import com.utndds.heladerasApi.repositories.reportesRepositories.ReporteFallasHeladeraRepository;
import com.utndds.heladerasApi.repositories.reportesRepositories.ReporteViandasMovidasColaboradorRepository;
import com.utndds.heladerasApi.repositories.reportesRepositories.ReporteViandasMovidasHeladeraRepository;
import com.utndds.heladerasApi.services.Reportes.ReportesService;

public class ReportesServiceTest {

        @InjectMocks
        private ReportesService reportesService;

        @Mock
        private ReporteFallasHeladeraRepository reporteFallasHeladeraRepository;

        @Mock
        private ReporteViandasMovidasHeladeraRepository reporteViandasHeladeraRepository;

        @Mock
        private ReporteViandasMovidasColaboradorRepository reporteViandasColaboradorRepository;

        @BeforeEach
        public void setUp() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testObtenerReporteFallasPorRangoUltimaSemana() {
                // Datos de prueba
                FallasPorHeladera reporte1 = new FallasPorHeladera(1L, 3);
                FallasPorHeladera reporte2 = new FallasPorHeladera(2L, 5);
                LocalDate fechaInicio = LocalDate.now().minusDays(7);
                LocalDate fechaFin = LocalDate.now();

                when(reporteFallasHeladeraRepository.findByFechaGeneracionBetween(fechaInicio, fechaFin))
                                .thenReturn(Arrays.asList(reporte1, reporte2));

                // Llamar al método
                List<FallasPorHeladera> reportes = reportesService.obtenerReporteFallasPorRango(fechaInicio, fechaFin);

                // Verificar resultados
                assertNotNull(reportes);
                assertEquals(2, reportes.size());
                verify(reporteFallasHeladeraRepository).findByFechaGeneracionBetween(fechaInicio, fechaFin);
        }

        @Test
        public void testObtenerReporteViandasHeladeraPorRangoUltimaSemana() {
                // Datos de prueba
                ViandasMovidasPorHeladera reporte1 = new ViandasMovidasPorHeladera(1L, 10);
                ViandasMovidasPorHeladera reporte2 = new ViandasMovidasPorHeladera(2L, 15);
                LocalDate fechaInicio = LocalDate.now().minusDays(7);
                LocalDate fechaFin = LocalDate.now();

                when(reporteViandasHeladeraRepository.findByFechaGeneracionBetween(fechaInicio, fechaFin))
                                .thenReturn(Arrays.asList(reporte1, reporte2));

                // Llamar al método
                List<ViandasMovidasPorHeladera> reportes = reportesService
                                .obtenerReporteViandasHeladeraPorRango(fechaInicio, fechaFin);

                // Verificar resultados
                assertNotNull(reportes);
                assertEquals(2, reportes.size());
                verify(reporteViandasHeladeraRepository).findByFechaGeneracionBetween(fechaInicio, fechaFin);
        }

        @Test
        public void testObtenerReporteViandasColaboradorPorRangoUltimaSemana() {
                // Datos de prueba
                ViandasMovidasPorColaborador reporte1 = new ViandasMovidasPorColaborador(1L, 7);
                ViandasMovidasPorColaborador reporte2 = new ViandasMovidasPorColaborador(2L, 9);
                LocalDate fechaInicio = LocalDate.now().minusDays(7);
                LocalDate fechaFin = LocalDate.now();

                when(reporteViandasColaboradorRepository.findByFechaGeneracionBetween(fechaInicio, fechaFin))
                                .thenReturn(Arrays.asList(reporte1, reporte2));

                // Llamar al método
                List<ViandasMovidasPorColaborador> reportes = reportesService
                                .obtenerReporteViandasColaboradorPorRango(fechaInicio, fechaFin);

                // Verificar resultados
                assertNotNull(reportes);
                assertEquals(2, reportes.size());
                verify(reporteViandasColaboradorRepository).findByFechaGeneracionBetween(fechaInicio, fechaFin);
        }

        @Test
        public void testObtenerReporteFallasPorRangoSemanaEspecifica() {
                // Datos de prueba
                FallasPorHeladera reporte1 = new FallasPorHeladera(1L, 3);
                int numeroSemana = 1; // Semana actual
                LocalDate inicioSemana = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                                .minusWeeks(numeroSemana - 1);
                LocalDate finSemana = inicioSemana.plusDays(6);

                when(reporteFallasHeladeraRepository.findByFechaGeneracionBetween(inicioSemana, finSemana))
                                .thenReturn(Arrays.asList(reporte1));

                // Llamar al método
                List<FallasPorHeladera> reportes = reportesService.obtenerReporteFallasPorRango(inicioSemana,
                                finSemana);

                // Verificar resultados
                assertNotNull(reportes);
                assertEquals(1, reportes.size());
                verify(reporteFallasHeladeraRepository).findByFechaGeneracionBetween(inicioSemana, finSemana);
        }

        @Test
        public void testObtenerReporteViandasHeladeraPorRango() {
                // Datos de prueba
                ViandasMovidasPorHeladera reporte1 = new ViandasMovidasPorHeladera(1L, 10);
                LocalDate fechaInicio = LocalDate.now().minusDays(7);
                LocalDate fechaFin = LocalDate.now();

                when(reporteViandasHeladeraRepository.findByFechaGeneracionBetween(fechaInicio, fechaFin))
                                .thenReturn(Arrays.asList(reporte1));

                // Llamar al método
                List<ViandasMovidasPorHeladera> reportes = reportesService
                                .obtenerReporteViandasHeladeraPorRango(fechaInicio, fechaFin);

                // Verificar resultados
                assertNotNull(reportes);
                assertEquals(1, reportes.size());
                verify(reporteViandasHeladeraRepository).findByFechaGeneracionBetween(fechaInicio, fechaFin);
        }
}
