package com.utndds.heladerasApi.models.Heladera;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente;
import com.utndds.heladerasApi.models.Heladera.Sensores.SensorMovimiento;
import com.utndds.heladerasApi.models.Observer.ObservadorHeladera;
import com.utndds.heladerasApi.models.Suscripciones.Suscripcion;

public class Heladera implements ObservadorHeladera {
    Punto punto;
    int capacidad;
    LocalDate fechaInicioFuncionamiento;
    List<Vianda> viandas = new ArrayList<>();
    boolean funcionando;
    boolean abierta;
    ManejadorTemperatura manejadorTemperatura;
    SensorMovimiento sensorMov = new SensorMovimiento(this);
    List<Suscripcion> suscriptores = new ArrayList<>();
    List<Incidente> incidentes = new ArrayList<>();

    public Heladera(Punto punto, int capacidad, double minTemp, double maxTemp, boolean funcionando, boolean abierta,
            LocalDate fechaInicioFuncionamiento, List<Vianda> viandas) {
        this.punto = punto;
        this.capacidad = capacidad;
        this.funcionando = funcionando;
        this.abierta = abierta;
        this.fechaInicioFuncionamiento = fechaInicioFuncionamiento;
        this.viandas = viandas;
        this.manejadorTemperatura = new ManejadorTemperatura(this, minTemp, maxTemp);
    }

    @Override
    public void actualizarTemperatura(double temperatura) {
        this.manejadorTemperatura.actualizarTemperatura(temperatura);
    }

    public int cantMesesActiva() {
        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(fechaInicioFuncionamiento, hoy);
        int cantMeses = periodo.getYears() * 12 + periodo.getMonths();
        return cantMeses;
    }

    public void agregarSuscripcion(Suscripcion suscripcion) {
        this.suscriptores.add(suscripcion);
    }

    public void extraerVianda() {
        this.viandas.remove(this.viandas.size() - 1);
    }

    public void verificarSuscripciones() {
        for (Suscripcion suscripcion : suscriptores) {
            suscripcion.verificarNotificaciones();
        }
    }

    public int cantFallas() {
        return this.incidentes.size();
    }

    public int cantViandas() {
        return this.viandas.size();
    }

    public void setActiva(boolean abierta) {
        this.abierta = abierta;
    }

    public void setFuncionando(boolean funcionando) {
        this.funcionando = funcionando;
    }

    public Punto getPunto() {
        return punto;
    }

    public boolean getFuncionando() {
        return funcionando;
    }

}
