package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Vianda;
import com.utndds.heladerasApi.models.Rol.Colaborador;

import java.util.ArrayList;
import java.util.List;

public class DonacionVianda extends Colaboracion {
    List<Vianda> viandasDonadas = new ArrayList<>();
    Heladera heladera;

    public DonacionVianda(Colaborador colaborador,
            List<Vianda> viandasDonadas, Heladera heladera,
            boolean estado) {
        super(colaborador);
        this.viandasDonadas = viandasDonadas;
        this.heladera = heladera;
    }

    @Override
    protected void procesar() {
        super.procesar();
        System.out.println(
                "SE GUARDO LA DONACION DE VIANDAS POR PARTE DE: " + this.colaborador.getPersona().getNombre());
    }

    public double cantViandasDonadas() {
        return this.viandasDonadas.size();
    }

}
