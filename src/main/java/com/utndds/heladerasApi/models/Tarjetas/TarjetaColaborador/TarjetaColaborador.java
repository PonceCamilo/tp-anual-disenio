package com.utndds.heladerasApi.models.Tarjetas.TarjetaColaborador;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.ONG.ONG;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Solicitudes.SolicitudApertura;
import com.utndds.heladerasApi.models.Tarjetas.Tarjeta;
import jakarta.persistence.*;

@Entity
public class TarjetaColaborador extends Tarjeta {
    @OneToMany(mappedBy = "tarjetaColaborador", cascade = CascadeType.ALL)
    private List<Apertura> aperturas = new ArrayList<>();

    public TarjetaColaborador() {
        super(null); // Necesario para JPA
    }

    public TarjetaColaborador(Colaborador colaborador) {
        super(colaborador);
    }

    public boolean puedeUsarse(Heladera heladera) {
        List<SolicitudApertura> solicitudes = ONG.getInstance().getSolicitudes();
        return solicitudes.stream()
                .filter(solicitud -> solicitud.getColaborador() == this.dueño)
                .anyMatch(SolicitudApertura::getEstado);
    }

    protected void usar(Heladera heladera) {
        new Apertura(heladera, this);

    }

    public void agregarApertura(Apertura apertura) {
        this.aperturas.add(apertura);
    }

}
