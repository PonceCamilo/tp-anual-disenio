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

        this.procesar();
    }

    private void procesar() {
        this.incidente.agregarVisita(this);
        this.tecnico.agregarVisita(this);
        this.verificarArreglo();
    }

    private void verificarArreglo() {
        if (this.arreglado) {
            this.heladera.setFuncionando(true);
        } else {
            System.out.println("Se arregla otra visita");
        }

    }
}
