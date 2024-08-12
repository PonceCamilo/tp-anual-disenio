package com.utndds;

import java.util.List;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Sistema.Sistema;
import com.utndds.heladerasApi.HeladerasApiApplication;
import com.utndds.heladerasApi.controllers.CargaCSVController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = HeladerasApiApplication.class)
public class CargaCSVControllerTest {
    @Autowired
    private CargaCSVController cargaCSVController;

    @Test
    public void testCargarArchivoCSV() {
        String rutaArchivoCSV = ".\\src\\main\\resources\\colaboraciones.csv";
        cargaCSVController.cargarArchivoCSV(rutaArchivoCSV);

        List<Colaborador> colaboradores = Sistema.getInstance().getColaboradores();
        assertEquals(4, colaboradores.size());
        assertEquals("Jorge", colaboradores.get(0).getPersona().getNombre());
    }
}