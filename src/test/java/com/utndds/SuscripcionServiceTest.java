package com.utndds;

import com.utndds.heladerasApi.DTOs.SuscripcionDTO;
import com.utndds.heladerasApi.models.Enums.TipoContacto;
import com.utndds.heladerasApi.models.Enums.TipoEvento;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;
import com.utndds.heladerasApi.models.Persona.Contacto.Email;
import com.utndds.heladerasApi.models.Persona.Contacto.Whatsapp;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Suscripciones.Suscripcion;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.ContactoRepository;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.SuscripcionesRepositories.SuscripcionRepository;
import com.utndds.heladerasApi.services.SuscripcionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

public class SuscripcionServiceTest {

    @InjectMocks
    private SuscripcionService suscripcionService;

    @Mock
    private SuscripcionRepository suscripcionRepository;

    @Mock
    private HeladeraRepository heladeraRepository;

    @Mock
    private ContactoRepository contactoRepository;

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock
    private Colaborador colaborador;

    @Mock
    private Heladera heladera;

    @Mock
    private Persona persona;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configurar el colaborador y su persona
        colaborador = new Colaborador();
        colaborador.setPersona(persona);
    }

    @Test
    public void testSuscribirColaborador_SuscripcionExitosa() {
        // Datos de entrada
        SuscripcionDTO suscripcionDTO = new SuscripcionDTO();
        suscripcionDTO.setColaboradorId(1L);
        suscripcionDTO.setHeladeraId(1L);
        suscripcionDTO.setCantidadViandas(5);
        suscripcionDTO.setTiposContactosSeleccionados(Arrays.asList(TipoContacto.EMAIL, TipoContacto.WHATSAPP));
        suscripcionDTO.setTiposEventosSeleccionados(Arrays.asList(TipoEvento.POCAS_VIANDAS, TipoEvento.MUCHAS_VIANDAS));

        // Configuración de mocks
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.of(colaborador));
        when(heladeraRepository.findById(1L)).thenReturn(Optional.of(heladera));

        // Crear contactos mock
        Contacto emailContacto = mock(Email.class);
        Contacto whatsappContacto = mock(Whatsapp.class);

        when(contactoRepository.findByPersonaAndTipoIn(any(), any()))
                .thenReturn(Arrays.asList(emailContacto, whatsappContacto));

        // Guardar la suscripción mock
        when(suscripcionRepository.save(any(Suscripcion.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ejecutar el método
        Suscripcion result = suscripcionService.suscribirColaborador(suscripcionDTO);

        // Verificaciones
        assertNotNull(result);
        assertEquals(colaborador, result.getColaborador());
        assertEquals(heladera, result.getHeladera());
        assertEquals(2, result.getNotificacionesDeseadas().size()); // Se deben crear dos eventos

        // Verificar que se guardó la suscripción
        verify(suscripcionRepository, times(1)).save(any(Suscripcion.class));
    }

    @Test
    public void testSuscribirColaborador_ColaboradorNoEncontrado() {
        SuscripcionDTO suscripcionDTO = new SuscripcionDTO();
        suscripcionDTO.setColaboradorId(1L);

        when(colaboradorRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            suscripcionService.suscribirColaborador(suscripcionDTO);
        });

        assertEquals("Colaborador no encontrado", exception.getMessage());
    }

    @Test
    public void testSuscribirColaborador_HeladeraNoEncontrada() {
        SuscripcionDTO suscripcionDTO = new SuscripcionDTO();
        suscripcionDTO.setColaboradorId(1L);
        suscripcionDTO.setHeladeraId(1L);

        when(colaboradorRepository.findById(1L)).thenReturn(Optional.of(colaborador));
        when(heladeraRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            suscripcionService.suscribirColaborador(suscripcionDTO);
        });

        assertEquals("Heladera no encontrada", exception.getMessage());
    }
}