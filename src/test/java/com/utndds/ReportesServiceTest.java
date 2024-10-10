package com.utndds;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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
    public void testObtenerReporteFallasUltimaSemana() {
        // Datos de prueba
        FallasPorHeladera reporte1 = new FallasPorHeladera(LocalDate.now(), 1L, 3);
        FallasPorHeladera reporte2 = new FallasPorHeladera(LocalDate.now(), 2L, 5);
        when(reporteFallasHeladeraRepository.findByFechaGeneracionAfter(any(LocalDate.class)))
                .thenReturn(Arrays.asList(reporte1, reporte2));

        // Llamar al método
        List<FallasPorHeladera> reportes = reportesService.obtenerReporteFallasUltimaSemana();

        // Verificar resultados
        assertNotNull(reportes);
        assertEquals(2, reportes.size());
        verify(reporteFallasHeladeraRepository).findByFechaGeneracionAfter(any(LocalDate.class));
    }

    @Test
    public void testObtenerReporteViandasHeladeraUltimaSemana() {
        // Datos de prueba
        ViandasMovidasPorHeladera reporte1 = new ViandasMovidasPorHeladera(LocalDate.now(), 1L, 10);
        ViandasMovidasPorHeladera reporte2 = new ViandasMovidasPorHeladera(LocalDate.now(), 2L, 15);
        when(reporteViandasHeladeraRepository.findByFechaGeneracionAfter(any(LocalDate.class)))
                .thenReturn(Arrays.asList(reporte1, reporte2));

        // Llamar al método
        List<ViandasMovidasPorHeladera> reportes = reportesService.obtenerReporteViandasHeladeraUltimaSemana();

        // Verificar resultados
        assertNotNull(reportes);
        assertEquals(2, reportes.size());
        verify(reporteViandasHeladeraRepository).findByFechaGeneracionAfter(any(LocalDate.class));
    }

    @Test
    public void testObtenerReporteViandasColaboradorUltimaSemana() {
        // Datos de prueba
        ViandasMovidasPorColaborador reporte1 = new ViandasMovidasPorColaborador(LocalDate.now(), 1L, 7);
        ViandasMovidasPorColaborador reporte2 = new ViandasMovidasPorColaborador(LocalDate.now(), 2L, 9);
        when(reporteViandasColaboradorRepository.findByFechaGeneracionAfter(any(LocalDate.class)))
                .thenReturn(Arrays.asList(reporte1, reporte2));

        // Llamar al método
        List<ViandasMovidasPorColaborador> reportes = reportesService.obtenerReporteViandasColaboradorUltimaSemana();

        // Verificar resultados
        assertNotNull(reportes);
        assertEquals(2, reportes.size());
        verify(reporteViandasColaboradorRepository).findByFechaGeneracionAfter(any(LocalDate.class));
    }

    @Test
    public void testObtenerReporteFallasSemanaEspecifica() {
        // Datos de prueba
        FallasPorHeladera reporte1 = new FallasPorHeladera(LocalDate.now(), 1L, 3);
        when(reporteFallasHeladeraRepository.findByFechaGeneracionBetween(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Arrays.asList(reporte1));

        // Llamar al método
        List<FallasPorHeladera> reportes = reportesService.obtenerReporteFallasSemanaEspecifica(1);

        // Verificar resultados
        assertNotNull(reportes);
        assertEquals(1, reportes.size());
        verify(reporteFallasHeladeraRepository).findByFechaGeneracionBetween(any(LocalDate.class),
                any(LocalDate.class));
    }

    @Test
    public void testObtenerReportesViandasHeladeraPorRango() {
        // Datos de prueba
        ViandasMovidasPorHeladera reporte1 = new ViandasMovidasPorHeladera(LocalDate.now(), 1L, 10);
        when(reporteViandasHeladeraRepository.findByFechaGeneracionBetween(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Arrays.asList(reporte1));

        // Llamar al método
        List<ViandasMovidasPorHeladera> reportes = reportesService.obtenerReportesViandasHeladeraPorRango(
                LocalDate.now().minusDays(7), LocalDate.now());

        // Verificar resultados
        assertNotNull(reportes);
        assertEquals(1, reportes.size());
        verify(reporteViandasHeladeraRepository).findByFechaGeneracionBetween(any(LocalDate.class),
                any(LocalDate.class));
    }
}