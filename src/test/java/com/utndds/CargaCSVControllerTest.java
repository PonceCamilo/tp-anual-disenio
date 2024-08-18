package com.utndds;

import java.util.List;

import com.utndds.heladerasApi.models.ONG.ONG;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.HeladerasApiApplication;
import com.utndds.heladerasApi.controllers.CargaCSVController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = HeladerasApiApplication.class)
public class CargaCSVControllerTest {
    @Autowired
    private CargaCSVController cargaCSVController;

    @Test
    public void testCargarArchivoCSV() {
        try {
            // Ruta del archivo CSV
            Path path = Paths.get("./src/main/resources/colaboraciones.csv");
            byte[] content = Files.readAllBytes(path);

            // Crear un MockMultipartFile
            MultipartFile multipartFile = new MockMultipartFile("file", "colaboraciones.csv", "text/csv", content);

            // Llamar al método cargarArchivoCSV con el MockMultipartFile
            cargaCSVController.cargarArchivoCSV(multipartFile);

            // Verificar los colaboradores cargados
            List<Colaborador> colaboradores = ONG.getInstance().getColaboradores();
            assertEquals(4, colaboradores.size());
            assertEquals("Jorge", colaboradores.get(0).getPersona().getNombre());
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo del error: puede lanzar una excepción en la prueba si falla
            throw new RuntimeException("Error al leer el archivo CSV en la prueba.", e);
        }
    }
}