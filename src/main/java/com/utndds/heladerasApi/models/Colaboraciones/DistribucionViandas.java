package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import javax.persistence.*;

@Entity
public class DistribucionViandas extends Colaboracion {

    @ManyToOne
    @JoinColumn(name = "heladera_origen")
    private Heladera heladeraOrigen;

    @ManyToOne
    @JoinColumn(name = "heladera_destino")
    private Heladera heladeraDestino;

    @Column(name = "cantidad_viandas_a_mover")
    private int cantidadViandasAMover;

    @Column(name = "motivo")
    private String motivo;

    // Constructor vacío para JPA
    public DistribucionViandas() {
    }

    public DistribucionViandas(Colaborador colaborador, Heladera heladeraOrigen,
            Heladera heladeraDestino, int cantidadViandasAMover, String motivo) {
        super(colaborador);
        this.heladeraOrigen = heladeraOrigen;
        this.heladeraDestino = heladeraDestino;
        this.cantidadViandasAMover = cantidadViandasAMover;
        this.motivo = motivo;
    }

    @Override
    protected void procesar() {
        super.procesar();
        System.out.println(
                "SE GUARDO LA DISTRIBUCION VIANDAS POR PARTE DE: " + this.colaborador.getPersona().getNombre());
    }

    public int getCantidadViandasAMover() {
        return cantidadViandasAMover;
    }
}
