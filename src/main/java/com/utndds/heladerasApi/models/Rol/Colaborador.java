package com.utndds.heladerasApi.models.Rol;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Suscripciones.Suscripcion;
import jakarta.persistence.*;

@Entity
@Table(name = "colaborador")
public class Colaborador extends Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Colaboracion> colaboraciones = new ArrayList<>();

    @OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Suscripcion> suscripciones = new ArrayList<>();

    // Constructor vacío para JPA
    public Colaborador() {
    }

    public Colaborador(Persona persona, List<Colaboracion> colaboraciones) {
        super(persona);
        this.colaboraciones = colaboraciones;
    }

    public Persona getPersona() {
        return (Persona) this.persona;
    }

    public double puntos() {
        double puntos = 0;
        for (Colaboracion colaboracion : colaboraciones) {
            puntos += colaboracion.puntosGanados();
        }

        return puntos;
    }

    public void agregarColaboracion(Colaboracion colaboracion) {
        colaboraciones.add(colaboracion);
    }

    public void agregarSuscripcion(Suscripcion suscripcion) {
        suscripciones.add(suscripcion);
    }

    public List<Colaboracion> getColaboraciones() {
        return colaboraciones;
    }

}
