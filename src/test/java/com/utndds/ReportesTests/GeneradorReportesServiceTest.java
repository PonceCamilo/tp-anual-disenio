package com.utndds.ReportesTests;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.utndds.heladerasApi.services.Reportes.GeneradorReportes.GeneradorReportesService;
import com.utndds.heladerasApi.services.Reportes.GeneradorReportes.Reportes.GeneradorReporte;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class GeneradorReportesServiceTest {

    @InjectMocks
    private GeneradorReportesService generadorReportesService;

    @Mock
    private GeneradorReporte mockReporteFallas;

    @Mock
    private GeneradorReporte mockReporteViandasPorColaborador;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerarReportesSemanales() {
        // Configuración de fechas de prueba
        LocalDate fechaInicial = LocalDate.now().minusDays(7);
        LocalDate fechaFinal = LocalDate.now();

        // Configuración de los mocks
        doNothing().when(mockReporteFallas).generar(fechaInicial, fechaFinal);
        doNothing().when(mockReporteViandasPorColaborador).generar(fechaInicial, fechaFinal);

        // Simulamos que la lista de reportes contiene nuestros mocks
        List<GeneradorReporte> reportes = Arrays.asList(mockReporteFallas, mockReporteViandasPorColaborador);
        generadorReportesService = new GeneradorReportesService(reportes);

        // Llamar al método a probar
        generadorReportesService.generarReportesSemanales();

        // Verificar que se llamaron los métodos de generación de reportes con las
        // fechas correctas
        verify(mockReporteFallas, times(1)).generar(fechaInicial, fechaFinal);
        verify(mockReporteViandasPorColaborador, times(1)).generar(fechaInicial, fechaFinal);
    }

    @Test
    public void testGenerarReportesSemanalesConExcepcion() {
        // Configuración de fechas de prueba
        LocalDate fechaInicial = LocalDate.now().minusDays(7);
        LocalDate fechaFinal = LocalDate.now();

        // Configuración de los mocks
        doThrow(new RuntimeException("Error en generación de reporte"))
                .when(mockReporteFallas).generar(fechaInicial, fechaFinal);
        doNothing().when(mockReporteViandasPorColaborador).generar(fechaInicial, fechaFinal);

        // Simulamos que la lista de reportes contiene nuestros mocks
        List<GeneradorReporte> reportes = Arrays.asList(mockReporteFallas, mockReporteViandasPorColaborador);
        generadorReportesService = new GeneradorReportesService(reportes);

        // Llamar al método a probar
        generadorReportesService.generarReportesSemanales();

        // Verificamos que el reporte que genera excepción se haya manejado
        verify(mockReporteFallas, times(1)).generar(fechaInicial, fechaFinal);
        verify(mockReporteViandasPorColaborador, times(1)).generar(fechaInicial, fechaFinal);
    }
}
