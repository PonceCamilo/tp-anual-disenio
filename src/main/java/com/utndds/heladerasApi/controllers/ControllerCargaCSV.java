package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.models.CargaCSV.CargaCSV;
import com.utndds.heladerasApi.models.Persona.Colaboradores.PersonaHumana;
import java.util.List;

public class ControllerCargaCSV {
    public String cargarArchivoCSV(String filePath) {
        CargaCSV cargaCSV = new CargaCSV();
        List<PersonaHumana> personas = cargaCSV.cargarArchivoCSV(filePath);
        for (PersonaHumana persona : personas) {
            System.out.println("Colaborador: " + persona.getNombre() + " " + persona.getApellido());
        }
        return "Archivo cargado con éxito";
    }

    // PARA PROBAR EL CONTROLADOR
    public static void main(String[] args) {
        ControllerCargaCSV controller = new ControllerCargaCSV();
        String rutaArchivoCSV = ".\\tp-anual-disenio\\src\\main\\resources\\colaboraciones.csv";
        String resultado = controller.cargarArchivoCSV(rutaArchivoCSV);
        System.out.println(resultado);
    }
}
