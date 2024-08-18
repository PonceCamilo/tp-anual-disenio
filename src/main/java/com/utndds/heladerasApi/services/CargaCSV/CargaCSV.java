package com.utndds.heladerasApi.services.CargaCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.utndds.heladerasApi.models.ONG.ONG;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;

import org.springframework.stereotype.Service;

import java.util.List;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class CargaCSV {
    private PersonaHumanaFactory phFactory = new PersonaHumanaFactory();
    private ColaboradorFactory cFactory = new ColaboradorFactory();
    private ColaboracionFactory colaboFactory = new ColaboracionFactory();

    public void cargarCSV(InputStream fileInputStream) {
        ONG sistema = ONG.getInstance();

        try (CSVReader reader = new CSVReader(new InputStreamReader(fileInputStream))) {
            List<String[]> registros = reader.readAll();
            for (String[] registro : registros) {
                PersonaHumana persona = phFactory.crearPersonaHumana(registro);

                Colaborador colaborador = sistema.buscarColaborador(registro[0], registro[1]);
                if (colaborador == null) {
                    colaborador = cFactory.crearColaborador(registro, persona);
                    sistema.agregarColaborador(colaborador);
                }

                System.out.println("el colaborador no existe: " + colaborador.getPersona());
                colaboFactory.crearColaboracion(registro, colaborador);
                System.out.println(colaborador.getPersona().getNombre());
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Ruta del archivo CSV
            Path path = Paths.get("./src/main/resources/colaboraciones.csv");
            byte[] content = Files.readAllBytes(path);

            // Crear un InputStream desde el contenido del archivo
            InputStream inputStream = new ByteArrayInputStream(content);

            // Instanciar CargaCSV
            CargaCSV cargaCSV = new CargaCSV();
            // Llamar al método cargarCSV con el InputStream
            cargaCSV.cargarCSV(inputStream);

            // Verificar los colaboradores cargados
            List<Colaborador> colaboradores = ONG.getInstance().getColaboradores();
            System.out.println("Número de colaboradores: " + colaboradores.size());
            for (Colaborador colaborador : colaboradores) {
                System.out.println("Colaborador: " + colaborador.getPersona().getNombre());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al leer el archivo CSV.", e);
        }
    }
}