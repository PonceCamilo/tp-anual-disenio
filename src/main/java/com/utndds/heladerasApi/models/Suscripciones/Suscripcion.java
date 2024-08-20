package com.utndds.heladerasApi.models.Suscripciones;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Observer.ObservadorSuscripcion;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Suscripciones.Evento.Evento;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Suscripcion implements ObservadorSuscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "heladera")
    private Heladera heladera;

    @Getter
    @ManyToOne
    @JoinColumn(name = "colaborador")
    private Colaborador colaborador;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "suscripcion")
    private List<Evento> notificacionesDeseadas = new ArrayList<>();

    // Constructor vacío para JPA
    public Suscripcion() {
    }

    public Suscripcion(Heladera heladera, Colaborador colaborador) {
        this.heladera = heladera;
        this.colaborador = colaborador;

        this.procesar();
    }

    public void verificarNotificaciones(Heladera heladera) {
        // FALTA IMPLEMENTAR
    };

    private void procesar() {
        this.heladera.agregarSuscripcion(this);
        this.colaborador.agregarSuscripcion(this);
    };

    public void verificarNotificaciones() {
        for (Evento evento : notificacionesDeseadas) {
            evento.verificarEvento(this.heladera);
        }
    }

}
