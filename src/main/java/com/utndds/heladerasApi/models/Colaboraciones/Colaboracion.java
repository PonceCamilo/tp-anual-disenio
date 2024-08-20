package com.utndds.heladerasApi.models.Colaboraciones;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Rol.Colaborador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Colaboracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "colaborador")
    protected Colaborador colaborador;

    // Constructor vacío para JPA
    public Colaboracion() {
    }

    public Colaboracion(Colaborador colaborador) {
        this.fecha = LocalDate.now();
        this.colaborador = colaborador;
        this.procesar();
    }

    protected void procesar() {
        colaborador.agregarColaboracion(this);
        this.notificarColaborador("La colaboracion ha sido recibida correctamente.");
    }

    protected void notificarColaborador(String mensaje) {
        colaborador.notificar(mensaje);
    };

    public double puntosGanados() {
        return 0;
    };

    protected double obtenerCoeficiente() {
        return 0;
    };

}
