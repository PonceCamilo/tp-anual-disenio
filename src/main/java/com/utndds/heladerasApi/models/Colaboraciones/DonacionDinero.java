package com.utndds.heladerasApi.models.Colaboraciones;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utndds.heladerasApi.models.Persona.Colaboradores.Colaborador;

public class DonacionDinero extends Colaboracion {
    double monto;
    int frecuencia;

    public DonacionDinero(LocalDate fecha, Colaborador colaborador, double monto, int frecuencia) {
        super(fecha, colaborador);
        this.monto = monto;
        this.frecuencia = frecuencia;
    }

    @Override
    public void realizar() {
        System.out.println("SE REALIZO LA DONACION DE DINERO");
    }

    @Override
    public double puntosGanados() {
        return this.monto * this.obtenerCoeficiente();
    }

    @Override
    protected double obtenerCoeficiente() {
        try {
            String projectDir = Paths.get("").toAbsolutePath().toString();
            String jsonFilePath = projectDir + "/src/main/resources/coeficientes.json";
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(jsonFilePath));
            JsonNode coeficientesNode = rootNode.path("coeficientes");
            return coeficientesNode.path("PESOS_DONADOS").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
            // Maneja la excepción, aquí puedes lanzar una excepción personalizada si es
            // necesario
            return 0;
        }
    }
}
