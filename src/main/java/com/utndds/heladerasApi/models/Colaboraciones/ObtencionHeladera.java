package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Persona.Colaboradores.Colaborador;

import java.time.LocalDate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ObtencionHeladera extends Colaboracion {
    Heladera heladera;

    public ObtencionHeladera(LocalDate fecha, Colaborador colaborador, Heladera heladera) {
        super(fecha, colaborador);
        this.heladera = heladera;
    }

    @Override
    public void realizar() {
        System.out.println("SE REALIZADO LA OBTENCION DE LA HELADERA");
    }

    @Override
    public double puntosGanados() {
        System.out.println("CORREGIR PUNTOS GANADOS");
        return 25;
    }

    @Override
    protected double obtenerCoeficiente() {
        try {
            String projectDir = Paths.get("").toAbsolutePath().toString();
            String jsonFilePath = projectDir + "/src/main/resources/coeficientes.json";
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(jsonFilePath));
            JsonNode coeficientesNode = rootNode.path("coeficientes");
            return coeficientesNode.path("CANTIDAD_HELADERAS_ACTIVAS").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
            // Maneja la excepción, aquí puedes lanzar una excepción personalizada si es
            // necesario
            return 0;
        }
    }

    public static void main(String[] args) {
        ObtencionHeladera colaboracion = new ObtencionHeladera(null, null, null);
        System.out.println(colaboracion.puntosGanados());

    }

}
