package com.utndds;

import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.PersonaRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.ColaboracionRepository;
import com.utndds.heladerasApi.services.CargaCSV.CargaCSV;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CargaCSVTest {

    @InjectMocks
    private CargaCSV cargaCSV;

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private ColaboracionRepository colaboracionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCargarCSV_CreaNuevaPersonaYColaborador() throws Exception {
        String csvData = "12345678,12345678,John,Doe,johndoe@example.com,DINERO,100\n";
        InputStream inputStream = new ByteArrayInputStream(csvData.getBytes());

        // Mock para repositories
        when(personaRepository.findByDocumento_TipoAndDocumento_Numero("DNI", "12345678"))
                .thenReturn(null); // Simular q la persona no existe

        cargaCSV.cargarCSV(inputStream);

        verify(personaRepository, times(1)).save(any(PersonaHumana.class));
        verify(colaboradorRepository, times(1)).save(any(Colaborador.class));
        verify(colaboracionRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testCargarCSV_HandlesInvalidData() throws Exception {
        String csvData = "12345678,12345678\n"; // datos faltantes
        InputStream inputStream = new ByteArrayInputStream(csvData.getBytes());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cargaCSV.cargarCSV(inputStream);
        });

        String expectedMessage = "Registro debe tener al menos 5 campos.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}