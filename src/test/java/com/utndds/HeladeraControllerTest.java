package com.utndds;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.utndds.heladerasApi.HeladerasApiApplication;
import com.utndds.heladerasApi.models.Heladera.Punto;

@SpringBootTest(classes = HeladerasApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Opcional, si necesitas perfiles de configuración especiales para pruebas
public class HeladeraControllerTest {

        @Autowired
        private TestRestTemplate restTemplate;

        @Test
        public void testRecomendarPuntosColocacion() {
                // Realiza la solicitud GET
                ResponseEntity<Punto[]> response = restTemplate.getForEntity(
                                "/heladeras/recomendarPuntos?latitud=1&longitud=1&radio=1", Punto[].class);

                // Verifica que el estado de la respuesta sea 200 OK
                assertEquals(HttpStatus.OK, response.getStatusCode());

                // Obtiene el cuerpo de la respuesta
                List<Punto> puntos = Arrays.asList(response.getBody());

                // Crea una lista esperada de puntos esperados
                List<Punto> puntosEsperados = Arrays.asList(
                                new Punto(1.01, 1.01, "heladera UTN Medrano", "Av. Medrano 951"),
                                new Punto(0.99, 0.99, "heladera UTN Campus", "Mozart 2300"));

                // Compara cada punto en la lista obtenida con el punto esperado
                for (int i = 0; i < puntosEsperados.size(); i++) {
                        Punto puntoEsperado = puntosEsperados.get(i);
                        Punto puntoObtenido = puntos.get(i);

                        assertEquals(puntoEsperado.getLatitud(), puntoObtenido.getLatitud(),
                                        "La latitud no coincide en el punto " + i);
                        assertEquals(puntoEsperado.getLongitud(), puntoObtenido.getLongitud(),
                                        "La longitud no coincide en el punto " + i);
                        assertEquals(puntoEsperado.getNombre(), puntoObtenido.getNombre(),
                                        "El nombre no coincide en el punto " + i);
                        assertEquals(puntoEsperado.getDireccion(), puntoObtenido.getDireccion(),
                                        "La dirección no coincide en el punto " + i);
                }
        }
}