package com.utndds.heladerasApi.services.CargaCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargaCSV {
    private PersonaHumanaFactory phFactory = new PersonaHumanaFactory();
    private ColaboradorFactory cFactory = new ColaboradorFactory();
    private ColaboracionFactory colaboFactory = new ColaboracionFactory();

    public List<Colaboracion> cargarCSV(String filePath) {
        List<Colaboracion> colaboracionesCargadas = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> registros = reader.readAll();
            for (String[] registro : registros) {
                PersonaHumana persona = phFactory.crearPersonaHumana(registro);
                Colaborador colaborador = cFactory.crearColaborador(registro, persona);
                List<Colaboracion> colaboracion = colaboFactory.crearColaboracion(registro, colaborador);
                colaboracionesCargadas.addAll(colaboracion);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return colaboracionesCargadas;
    }

    public static void main(String[] args) {

    }
}