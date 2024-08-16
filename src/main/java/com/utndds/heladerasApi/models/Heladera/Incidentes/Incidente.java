package com.utndds.heladerasApi.models.Heladera.Incidentes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

import com.utndds.heladerasApi.models.Heladera.Heladera;

import lombok.Getter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Utiliza JOINED si Incidente es una clase base para otras entidades
public abstract class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "heladera") // Nombre de la columna de la FK en la tabla Incidente
    protected Heladera heladera;

    @OneToMany(mappedBy = "incidente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VisitaTecnico> visitas = new ArrayList<>();

    // Constructor vacío para JPA
    public Incidente() {
    }

    public Incidente(Heladera heladera) {
        this.fechaHora = LocalDateTime.now();
        this.heladera = heladera;

        this.procesar();
    }

    public void procesar() {
        this.desactivarHeladera();
    };

    private void desactivarHeladera() {
        this.heladera.setFuncionando(false);
    }

    public void agregarVisita(VisitaTecnico visita) {
        this.visitas.add(visita);
    }
}
