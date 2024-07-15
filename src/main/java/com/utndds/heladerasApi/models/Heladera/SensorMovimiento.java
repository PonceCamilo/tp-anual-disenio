package com.utndds.heladerasApi.models.Heladera;

public class SensorMovimiento implements Observador {

    Heladera heladera;

    public SensorMovimiento(Heladera heladera) {
        this.heladera = heladera;
        this.heladera.agregarObservador(this);
    }

    @Override
    public void actualizar() {
        System.out.println("Fraude detectado en la heladera" + heladera.getNombre());
    }
}