package com.utndds;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import static org.springframework.http.MediaType.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.utndds.heladerasApi.HeladerasApiApplication;
import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.services.ReconocimientosExtra;

import java.util.List;

@SpringBootTest(classes = HeladerasApiApplication.class)
public class ReconocimientosExtraTest {

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ReconocimientosExtra reconocimientosExtra;

    @BeforeEach
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        reconocimientosExtra = new ReconocimientosExtra(restTemplate);
    }

    @Test
    public void testRecomendarColaboradores_CumplenRequisitos() {
        // Crear la respuesta mock con datos de personas
        String responseJson = """
                [
                    {"nombre": "Colaborador 1"},
                    {"nombre": "Colaborador 2"}
                ]
                """;

        mockServer.expect(requestTo(
                "http://localhost:8082/service-2/recomendaciones-colaboradores?puntosReq=100,000000&viandasDonadasReq=1,000000&cantMaxColabs=2"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseJson, APPLICATION_JSON));

        // Ejecutar método
        List<Persona> colaboradoresRecomendados = reconocimientosExtra.recomendarColaboradores(100.0, 1.0, 2);

        // Validaciones
        assertNotNull(colaboradoresRecomendados);
        assertEquals(2, colaboradoresRecomendados.size());
        assertEquals("Colaborador 1", colaboradoresRecomendados.get(0).getNombre());
        assertEquals("Colaborador 2", colaboradoresRecomendados.get(1).getNombre());

        // Verificar que el servidor mock respondió como se esperaba
        mockServer.verify();
    }

    @Test
    public void testRecomendarColaboradores_NoCumplenRequisitos() {
        // Simular una respuesta vacía del servicio remoto
        String responseJson = "[]";

        mockServer.expect(requestTo(
                "http://localhost:8082/service-2/recomendaciones-colaboradores?puntosReq=100.0&viandasDonadasReq=1.0&cantMaxColabs=2"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseJson, APPLICATION_JSON));

        // Ejecutar método
        List<Persona> colaboradoresRecomendados = reconocimientosExtra.recomendarColaboradores(100.0, 1.0, 2);

        // Validaciones
        assertNotNull(colaboradoresRecomendados);
        assertTrue(colaboradoresRecomendados.isEmpty());

        // Verificar que el servidor mock respondió como se esperaba
        mockServer.verify();
    }
}
