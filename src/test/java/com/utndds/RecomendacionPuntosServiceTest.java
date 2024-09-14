package com.utndds;

import com.utndds.heladerasApi.controllers.DTOs.RecomendacionDTO;
import com.utndds.heladerasApi.services.RecomendacionPuntosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RecomendacionPuntosServiceTest {

    private RecomendacionPuntosService recomendacionPuntosService;

    @BeforeEach
    public void setUp() {
        recomendacionPuntosService = new RecomendacionPuntosService();
    }

    @Test
    public void testGetRecomendaciones() {
        // Coordenadas de ejemplo (latitud, longitud) y radio de búsqueda
        double latitud = -34.603722; // Buenos Aires
        double longitud = -58.381592;
        double radio = 5000; // 5 km

        // Llamada al método a testear
        List<RecomendacionDTO> recomendaciones = recomendacionPuntosService.getRecomendaciones(latitud, longitud, radio);

        // Verifica que se hayan generado 10 recomendaciones
        assertEquals(10, recomendaciones.size(), "Debería haber 10 recomendaciones generadas");

        // Verifica que cada recomendación esté dentro del radio esperado
        for (RecomendacionDTO recomendacion : recomendaciones) {
            double distancia = calcularDistancia(latitud, longitud, recomendacion.getLatitud(), recomendacion.getLongitud());
            assertTrue(distancia <= radio, "La recomendación debería estar dentro del radio especificado");
        }
    }

    // Método auxiliar para calcular la distancia entre dos puntos geográficos usando la fórmula de Haversine
    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final double RADIO_TIERRA = 6371000; // radio de la Tierra en metros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RADIO_TIERRA * c;
    }
}
