package com.utndds.heladerasApi;

import com.utndds.heladerasApi.models.Colaboraciones.*;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Vianda;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.PersonaVulnerable;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln.TarjetaPersVuln;
import com.utndds.heladerasApi.services.CalculadoraPuntosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CalculadoraPuntosServiceTest {

    @InjectMocks
    CalculadoraPuntosService calculadora;

    @Mock
    Colaborador colaborador;

    @Mock
    PersonaHumana persona;

    @Mock
    Vianda vianda;

    @Mock
    Heladera heladera;

    @Mock
    PersonaVulnerable personaVulnerable1;
    @Mock
    PersonaVulnerable personaVulnerable2;

    @Mock
    TarjetaPersVuln tarjeta1;

    @Mock
    TarjetaPersVuln tarjeta2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        List<Colaboracion> colaboraciones = new ArrayList<>();
        when(colaborador.getColaboraciones()).thenReturn(colaboraciones);

        when(colaborador.getPersona()).thenReturn(persona);
        when(colaborador.getPersona().getNombre()).thenReturn("Miller");

    }

    @Test
    public void testNoColaboraciones() {
        assertEquals(0.0, calculadora.calcularPuntos(colaborador));
    }

    @Test
    public void testColaboracionesDinero() {
        DonacionDinero donacion = new DonacionDinero(colaborador, 100, 0);
        colaborador.getColaboraciones().add(donacion);
        colaborador.getColaboraciones().add(donacion);
        colaborador.getColaboraciones().add(donacion);

        assertEquals(150.0, calculadora.calcularPuntos(colaborador));
    }

    @Test
    public void testViandasDonadas() {
        DonacionVianda donacion = new DonacionVianda(colaborador, vianda, heladera);
        colaborador.getColaboraciones().add(donacion);
        colaborador.getColaboraciones().add(donacion);
        colaborador.getColaboraciones().add(donacion);

        assertEquals(4.5, calculadora.calcularPuntos(colaborador));
    }

    @Test
    public void testTarjetasRepartidas() {
        RegistroPersonaVulnerable registroPersona1 = new RegistroPersonaVulnerable(colaborador, personaVulnerable1, tarjeta1);
        RegistroPersonaVulnerable registroPersona2 = new RegistroPersonaVulnerable(colaborador, personaVulnerable2, tarjeta2);

        colaborador.getColaboraciones().add(registroPersona1);
        colaborador.getColaboraciones().add(registroPersona2);

        assertEquals(4, calculadora.calcularPuntos(colaborador));
    }

    @Test
    public void testHeladeraNueva() {
        ObtencionHeladera obtencion = new ObtencionHeladera(colaborador, heladera);

        colaborador.getColaboraciones().add(obtencion);

        when(obtencion.getHeladera().cantMesesActiva()).thenReturn(0);

        assertEquals(0, calculadora.calcularPuntos(colaborador));
    }

    @Test
    public void testHeladera10Meses() {
        ObtencionHeladera obtencion = new ObtencionHeladera(colaborador, heladera);

        colaborador.getColaboraciones().add(obtencion);

        when(heladera.cantMesesActiva()).thenReturn(10);

        assertEquals(50, calculadora.calcularPuntos(colaborador));
    }


    @Test
    public void testHeladeras5Meses() {
        ObtencionHeladera obtencion = new ObtencionHeladera(colaborador, heladera);
        ObtencionHeladera obtencion2 = new ObtencionHeladera(colaborador, heladera);

        colaborador.getColaboraciones().add(obtencion);
        colaborador.getColaboraciones().add(obtencion2);

        when(obtencion.getHeladera().cantMesesActiva()).thenReturn(5);
        when(obtencion.getHeladera().cantMesesActiva()).thenReturn(5);

        assertEquals(100, calculadora.calcularPuntos(colaborador));
    }

    @Test
    public void testDistintasColaboraciones() {
        RegistroPersonaVulnerable registroPersona1 = new RegistroPersonaVulnerable(colaborador, personaVulnerable1, tarjeta1);
        RegistroPersonaVulnerable registroPersona2 = new RegistroPersonaVulnerable(colaborador, personaVulnerable2, tarjeta2);
        DonacionDinero donacionDinero = new DonacionDinero(colaborador, 250, 0);
        DonacionVianda donacionVianda = new DonacionVianda(colaborador, vianda, heladera);

        colaborador.getColaboraciones().add(registroPersona1);
        colaborador.getColaboraciones().add(registroPersona2);
        colaborador.getColaboraciones().add(donacionDinero);
        colaborador.getColaboraciones().add(donacionVianda);

        assertEquals(130.5, calculadora.calcularPuntos(colaborador));
    }
}
