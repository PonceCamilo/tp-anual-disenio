package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Colaborador;

public class DistribucionViandas extends Colaboracion {
    Heladera heladeraOrigen;
    Heladera heladeraDestino;
    int cantidadViandasAMover;
    String motivo;

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
