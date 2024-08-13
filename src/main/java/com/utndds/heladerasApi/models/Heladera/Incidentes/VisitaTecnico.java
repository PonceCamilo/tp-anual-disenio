package com.utndds.heladerasApi.models.Heladera.Incidentes;

import java.time.LocalDate;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Tecnico;

public class VisitaTecnico {
    LocalDate fecha;
    Tecnico tecnico;
    Heladera heladera;
    Incidente incidente;
    String descripcion;
    File foto;
    boolean arreglado;

    public VisitaTecnico(LocalDate fecha, Tecnico tecnico, Heladera heladera, Incidente incidente, String descripcion,
            File foto, boolean arreglado) {
        this.fecha = fecha;
        this.tecnico = tecnico;
        this.heladera = heladera;
        this.incidente = incidente;
        this.descripcion = descripcion;
        this.foto = foto;
        this.arreglado = arreglado;

        this.verificarArreglo();
    }

    private void verificarArreglo() {
        System.out.println("se verifica el arreglo por parte del tecnico, sino se arregla se plantea otra visita");
    }
}
