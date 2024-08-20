package com.utndds.heladerasApi.models.Heladera.Incidentes;

import java.time.LocalDate;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.utndds.heladerasApi.models.Rol.Tecnico;
import jakarta.persistence.*;

@Entity
@Table(name = "visita_tecnico")
public class VisitaTecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnico")
    private Tecnico tecnico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidente")
    private Incidente incidente;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "foto")
    private File foto;

    @Column(name = "arreglado")
    private boolean arreglado;

    // Constructor vacío para JPA
    public VisitaTecnico() {
    }

    public VisitaTecnico(LocalDate fecha, Tecnico tecnico, Incidente incidente, String descripcion,
            File foto, boolean arreglado) {
        this.fecha = fecha;
        this.tecnico = tecnico;
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
            this.incidente.getHeladera().setFuncionando(true);
        } else {
            System.out.println("Se arregla otra visita");
        }

    }
}
