package com.utndds;

import com.utndds.heladerasApi.DTOs.RecomendacionDTO;
import com.utndds.heladerasApi.services.RecomendacionPuntosService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RecomendacionPuntosServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RecomendacionPuntosService recomendacionPuntosService;

    @SuppressWarnings("deprecation")
    @Test
    public void testGetRecomendaciones() {
        initMocks(this);

        RecomendacionDTO[] mockResponse = {
                new RecomendacionDTO("Punto 1", 40.7129, -74.0059),
                new RecomendacionDTO("Punto 2", 40.7130, -74.0058)
        };

        when(restTemplate.getForEntity(
                "http://localhost:8081/mockAPI/recomendarPuntos?latitud=40.712800&longitud=-74.006000&radio=1000.000000",
                RecomendacionDTO[].class))
                .thenReturn(ResponseEntity.ok(mockResponse));

        List<RecomendacionDTO> recomendaciones = recomendacionPuntosService.getRecomendaciones(40.7128, -74.0060, 1000);

        assertEquals(2, recomendaciones.size());
        assertEquals("Punto 1", recomendaciones.get(0).getNombre());
    }
}