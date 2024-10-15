package com.utndds;

import com.utndds.heladerasApi.HeladerasApiApplication;
import com.utndds.heladerasApi.models.Colaboraciones.DonacionViandas.DonacionVianda;
import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.ColaboracionRepository;
import com.utndds.heladerasApi.services.ReconocimientosExtra;
import com.utndds.heladerasApi.services.Canjes.CalculadoraPuntosService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Collections;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HeladerasApiApplication.class)
public class ReconocimientosExtraTest {

    @Mock
    private CalculadoraPuntosService calculadoraPuntosService;

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock
    private ColaboracionRepository colaboracionRepository;

    @InjectMocks
    private ReconocimientosExtra reconocimientosExtra;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRecomendarColaboradores_CumplenRequisitos() {
        // Mock colaborador1 y colaborador2
        Colaborador colaborador1 = mock(Colaborador.class);
        Colaborador colaborador2 = mock(Colaborador.class);

        // Crear y mockear las instancias de Persona
        Persona persona1 = mock(Persona.class);
        Persona persona2 = mock(Persona.class);

        // Establecer comportamiento de los mocks
        when(persona1.getNombre()).thenReturn("Colaborador 1");
        when(persona2.getNombre()).thenReturn("Colaborador 2");

        // Asignar las personas a los colaboradores
        when(colaborador1.getPersona()).thenReturn(persona1);
        when(colaborador2.getPersona()).thenReturn(persona2);

        // Mock datos de colaboradores
        when(colaboradorRepository.findAll()).thenReturn(Arrays.asList(colaborador1, colaborador2));

        // Mock puntos de colaboradores
        when(calculadoraPuntosService.calcularPuntos(colaborador1.getId())).thenReturn(150.0);
        when(calculadoraPuntosService.calcularPuntos(colaborador2.getId())).thenReturn(200.0);

        // Usar instancias reales de DonacionVianda
        DonacionVianda donacion1 = new DonacionVianda(colaborador1, null, null);
        donacion1.setFecha(LocalDate.now().minusDays(10)); // Establecer la fecha de la donación

        DonacionVianda donacion2 = new DonacionVianda(colaborador2, null, null);
        donacion2.setFecha(LocalDate.now().minusDays(5)); // Establecer la fecha de la donación

        // Mock viandas donadas
        when(colaboracionRepository.findByColaborador(colaborador1)).thenReturn(Arrays.asList(donacion1));
        when(colaboracionRepository.findByColaborador(colaborador2)).thenReturn(Arrays.asList(donacion2));

        // Ejecutar método
        List<Colaborador> colaboradoresRecomendados = reconocimientosExtra.recomendarColaboradores(100, 1, 2);

        // Validaciones
        assertEquals(2, colaboradoresRecomendados.size());
        assertTrue(colaboradoresRecomendados.contains(colaborador1));
        assertTrue(colaboradoresRecomendados.contains(colaborador2));
    }

    @Test
    public void testRecomendarColaboradores_NoCumplenRequisitos() {
        // Mock colaboradores
        Colaborador colaborador1 = mock(Colaborador.class);
        Colaborador colaborador2 = mock(Colaborador.class);

        // Mock datos de colaboradores
        when(colaboradorRepository.findAll()).thenReturn(Arrays.asList(colaborador1, colaborador2));

        // Mock puntos de colaboradores
        when(calculadoraPuntosService.calcularPuntos(colaborador1.getId())).thenReturn(50.0); // No alcanza el mínimo
        when(calculadoraPuntosService.calcularPuntos(colaborador2.getId())).thenReturn(80.0); // No alcanza el mínimo

        // Mock viandas donadas
        when(colaboracionRepository.findByColaborador(colaborador1)).thenReturn(Collections.emptyList());
        when(colaboracionRepository.findByColaborador(colaborador2)).thenReturn(Collections.emptyList());

        // Ejecutar método
        List<Colaborador> colaboradoresRecomendados = reconocimientosExtra.recomendarColaboradores(100, 1, 2);

        // Validaciones
        assertTrue(colaboradoresRecomendados.isEmpty());
    }

}