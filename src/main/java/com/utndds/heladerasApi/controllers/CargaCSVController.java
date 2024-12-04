package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.services.CargaCSV.CargaCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cargaCSV")
public class CargaCSVController {

    @Autowired
    private CargaCSV cargaCSV;

    @PostMapping("/cargarCSV")
    public ResponseEntity<String> cargarArchivoCSV(@RequestParam("file") MultipartFile file) {
        System.out.println("Cargando archivo CSV...");
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor suba un archivo");
        }
        System.out.println("el archivo no esta vacio");
        try {
            cargaCSV.cargarCSV(file.getInputStream());
            System.out.println("archivo cargado");
            return ResponseEntity.ok("Archivo CSV cargado exitosamente.");
        } catch (Exception e) {
            System.out.println("error al cargar el archivo");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cargar el archivo CSV: " + e.getMessage());
        }
    }
}