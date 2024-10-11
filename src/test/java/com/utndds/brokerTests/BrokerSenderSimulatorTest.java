package com.utndds.brokerTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.utndds.heladerasApi.config.RabbitMQConfig;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Sensores.Sensor;
import com.utndds.heladerasApi.models.Heladera.Sensores.SensorMovimiento;
import com.utndds.heladerasApi.models.Heladera.Sensores.SensorTemperatura;
import com.utndds.heladerasApi.models.Tarjetas.Tarjeta;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaColaborador;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.SensoresRepositories.SensoresRepository;
import com.utndds.heladerasApi.repositories.TarjetasRepositories.TarjetaRepository;
import com.utndds.heladerasApi.services.broker.BrokerSenderSimulator;

import java.util.ArrayList;
import java.util.List;

public class BrokerSenderSimulatorTest {

    @Mock
    private RabbitMQConfig rabbitMQConfig;

    @Mock
    private SensoresRepository sensoresRepository;

    @Mock
    private HeladeraRepository heladeraRepository;

    @Mock
    private TarjetaRepository tarjetaRepository;

    @Mock
    private Connection connection;

    @Mock
    private Channel channel;

    @InjectMocks
    private BrokerSenderSimulator brokerSenderSimulator;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(rabbitMQConfig.crearConexion()).thenReturn(connection);
        when(connection.createChannel()).thenReturn(channel);

        brokerSenderSimulator.init(); // Inicializamos el servicio
    }

    @Test
    public void testEnviarTemperaturas_ConSensores() throws Exception {
        // Mock de una lista de sensores de temperatura
        List<Sensor> sensores = new ArrayList<>();
        SensorTemperatura sensorTemperatura = new SensorTemperatura();
        sensorTemperatura.setId(1L);
        sensores.add(sensorTemperatura);

        when(sensoresRepository.findAll()).thenReturn(sensores);

        // Ejecutamos el método a probar
        brokerSenderSimulator.enviarTemperaturas();

        // Verificación: se debe haber publicado un mensaje en el canal
        verify(channel, times(1)).basicPublish(eq(""), eq("sensor_temperatura_queue"), any(), any(byte[].class));
    }

    @Test
    public void testEnviarSenalMovimiento_ConSensorMovimiento() throws Exception {
        // Mock de una lista de sensores de movimiento
        List<Sensor> sensores = new ArrayList<>();
        SensorMovimiento sensorMovimiento = new SensorMovimiento();
        sensorMovimiento.setId(2L);
        sensores.add(sensorMovimiento);

        when(sensoresRepository.findAll()).thenReturn(sensores);

        // Ejecutamos el método a probar
        brokerSenderSimulator.enviarSenalMovimiento();

        // Verificación: se debe haber publicado un mensaje en el canal
        verify(channel, times(1)).basicPublish(eq(""), eq("sensor_movimiento_queue"), any(), any(byte[].class));
    }

    @Test
    public void testEnviarSenalApertura_ConHeladeraYTarjeta() throws Exception {
        // Mock de una lista de heladeras
        List<Heladera> heladeras = new ArrayList<>();
        Heladera heladera = new Heladera();
        heladera.setId(1L);
        heladeras.add(heladera);

        // Mock de una tarjeta colaborador
        List<Tarjeta> tarjetas = new ArrayList<>();
        TarjetaColaborador tarjetaColaborador = new TarjetaColaborador();
        tarjetaColaborador.setId(1L); // Establecer el ID para la tarjeta
        tarjetas.add(tarjetaColaborador);

        when(heladeraRepository.findAll()).thenReturn(heladeras);
        when(tarjetaRepository.findAll()).thenReturn(tarjetas);

        // Ejecutamos el método a probar
        brokerSenderSimulator.enviarSenalApertura();

        // Verificación: se debe haber publicado un mensaje en el canal
        verify(channel, times(1)).basicPublish(eq(""), eq("tarjeta_queue"), any(), any(byte[].class));
    }

}