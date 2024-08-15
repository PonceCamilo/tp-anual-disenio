package com.utndds.heladerasApi.models.Rol;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln.TarjetaPersVuln;
import javax.persistence.*;

@Entity
@Table(name = "persona_vulnerable")
public class PersonaVulnerable extends Rol {

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "situacion_calle")
    private boolean situacionCalle;

    @Column(name = "cant_menores_acargo")
    private int cantMenoresAcargo;

    @ManyToOne
    @JoinColumn(name = "tarjeta")
    private TarjetaPersVuln tarjeta;

    // Constructor vacío para JPA
    public PersonaVulnerable() {
    }

    public PersonaVulnerable(Persona persona, boolean situacionCalle, int cantMenoresAcargo) {
        super(persona);
        this.fechaRegistro = LocalDate.now();
        this.situacionCalle = situacionCalle;
        this.cantMenoresAcargo = cantMenoresAcargo;
    }

    public void setTarjeta(TarjetaPersVuln tarjeta) {
        this.tarjeta = tarjeta;
    }

    public int getCantMenoresAcargo() {
        return cantMenoresAcargo;
    }

}