package com.utndds.brokerTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaColaborador;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.TarjetasRepositories.TarjetaRepository;
import com.utndds.heladerasApi.services.AperturaService;
import com.utndds.heladerasApi.services.Sensores.SensorMovimientoService;
import com.utndds.heladerasApi.services.Sensores.SensorTemperaturaService;
import com.utndds.heladerasApi.services.broker.BrokerReceiverService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BrokerReceiverServiceTest {

    @InjectMocks
    private BrokerReceiverService brokerReceiverService;

    @Mock
    private SensorTemperaturaService sensorTemperaturaService;

    @Mock
    private SensorMovimientoService sensorMovimientoService;

    @Mock
    private TarjetaRepository tarjetaRepository;

    @Mock
    private HeladeraRepository heladeraRepository;

    @Mock
    private AperturaService aperturaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRecibirTemperatura_ValidMessage() throws Exception {
        String mensaje = "{\"sensorId\": 1, \"temperatura\": 22.5}";

        brokerReceiverService.recibirTemperatura(mensaje);

        verify(sensorTemperaturaService, times(1)).procesarTemperatura(1L, 22.5);
    }

    @Test
    public void testRecibirMovimiento_ValidMessage() throws Exception {
        String mensaje = "{\"sensorId\": 2, \"tipo\": \"alerta\"}";

        brokerReceiverService.recibirMovimiento(mensaje);

        verify(sensorMovimientoService, times(1)).generarAlerta(2L, "alerta");
    }

    @Test
    public void testRecibirTarjeta_ValidMessage_CollaboratorCard() throws Exception {
        String mensaje = "{\"heladeraId\": 1, \"tarjetaId\": 1}";
        Heladera heladera = new Heladera();
        TarjetaColaborador tarjeta = new TarjetaColaborador();
        tarjeta.setId(1L);

        when(tarjetaRepository.findById(1L)).thenReturn(Optional.of(tarjeta));
        when(heladeraRepository.findById(1L)).thenReturn(Optional.of(heladera));
        when(aperturaService.manejarAperturaColaborador(heladera, tarjeta)).thenReturn("Apertura exitosa");

        brokerReceiverService.recibirTarjeta(mensaje);

        verify(aperturaService, times(1)).manejarAperturaColaborador(heladera, tarjeta);
    }

    @Test
    public void testRecibirTarjeta_ValidMessage_VulnerablePersonCard() throws Exception {
        String mensaje = "{\"heladeraId\": 1, \"tarjetaId\": 1}";
        Heladera heladera = new Heladera();
        TarjetaPersVuln tarjeta = new TarjetaPersVuln();
        tarjeta.setId(1L);

        when(tarjetaRepository.findById(1L)).thenReturn(Optional.of(tarjeta));
        when(heladeraRepository.findById(1L)).thenReturn(Optional.of(heladera));
        when(aperturaService.manejarAperturaPersonaVulnerable(heladera, tarjeta)).thenReturn("Apertura exitosa");

        brokerReceiverService.recibirTarjeta(mensaje);

        verify(aperturaService, times(1)).manejarAperturaPersonaVulnerable(heladera, tarjeta);
    }

    @Test
    public void testRecibirTarjeta_CardNotFound() throws Exception {
        String mensaje = "{\"heladeraId\": 1, \"tarjetaId\": 1}";

        when(tarjetaRepository.findById(1L)).thenReturn(Optional.empty());
        when(heladeraRepository.findById(1L)).thenReturn(Optional.empty());

        brokerReceiverService.recibirTarjeta(mensaje);

        // Aquí no deberíamos verificar métodos en aperturaService ya que la tarjeta o
        // heladera no fue encontrada
        verify(aperturaService, times(0)).manejarAperturaColaborador(any(), any());
        verify(aperturaService, times(0)).manejarAperturaPersonaVulnerable(any(), any());
    }

}