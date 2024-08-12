package com.utndds.heladerasApi.models.Heladera;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Incidentes.Alerta;
import com.utndds.heladerasApi.models.Heladera.Sensores.SensorMovimiento;
import com.utndds.heladerasApi.models.Rol.Colaborador;

public class Heladera implements Observador {
    Punto punto;
    int capacidad;
    LocalDate fechaInicioFuncionamiento;
    boolean funcionando;
    boolean abierta;
    double ultimaTempRegistrada;
    double minTemp;
    double maxTemp;
    SensorMovimiento sensorMov = new SensorMovimiento(this);
    List<SolicitudApertura> solicitudes = new ArrayList<>();
    List<Vianda> viandas = new ArrayList<>();

    public Heladera(Punto punto, int capacidad, double minTemp, double maxTemp, boolean funcionando, boolean abierta,
            LocalDate fechaInicioFuncionamiento, List<Vianda> viandas) {
        this.punto = punto;
        this.capacidad = capacidad;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.funcionando = funcionando;
        this.abierta = abierta;
        this.fechaInicioFuncionamiento = fechaInicioFuncionamiento;
        this.viandas = viandas;
    }

    @Override
    public void actualizarTemperatura(double temperatura) {
        this.ultimaTempRegistrada = temperatura;
        this.verificarTemperatura();
    }

    private void verificarTemperatura() {
        if (this.ultimaTempRegistrada < this.minTemp || this.ultimaTempRegistrada > this.maxTemp) {
            new Alerta(this, "Temperatura");
        }
    }

    public int cantMesesActiva() {
        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(fechaInicioFuncionamiento, hoy);
        int cantMeses = periodo.getYears() * 12 + periodo.getMonths();
        return cantMeses;
    }

    public void agregarSolicitud(SolicitudApertura solicitud) {
        this.solicitudes.add(solicitud);
    }

    public void eliminarSolicitud(SolicitudApertura solicitud) {
        this.solicitudes.remove(solicitud);
    }

    public void extraerVianda() {
        this.viandas.remove(this.viandas.size() - 1);
    }

    public void abrir(Colaborador colaborador) {
        SolicitudApertura solicitudCorrespondiente = this.obtenerSolicitud(colaborador);
        if (solicitudCorrespondiente != null) {
            System.out.println("El colaborador abrio la heladera con exito");
            this.solicitudes.remove(solicitudCorrespondiente);
            return;
        } else {
            System.out.println("El colaborador no hizo solicitud o la misma expiro");
        }
    }

    public SolicitudApertura obtenerSolicitud(Colaborador colaborador) {
        for (SolicitudApertura solicitud : solicitudes) {
            if (solicitud.getColaborador().equals(colaborador)) {
                return solicitud;
            }
        }
        return null;
    }

    public void verificarSuscripciones() {
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
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
}
