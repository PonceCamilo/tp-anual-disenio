package com.utndds.heladerasApi.models.Heladera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Heladera {
    String nombre;
    double longitud;
    double latitud;
    int capacidad;
    String direccion;
    double minTemp;
    double maxTemp;
    boolean estado;
    List<Observador> observadores = new ArrayList<>();
    LocalDate fechaInicioFuncionamiento;
    List<Vianda> viandas = new ArrayList<>();

    public Heladera(String nombre, double longitud, double latitud, int capacidad, String direccion, double minTemp,
            double maxTemp,
            boolean estado, LocalDate fechaInicioFuncionamiento, List<Vianda> viandas) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
        this.capacidad = capacidad;
        this.direccion = direccion;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.estado = estado;
        this.fechaInicioFuncionamiento = fechaInicioFuncionamiento;
        this.viandas = viandas;
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

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean getEstado() {
        return estado;
    }

    public void extraerVianda() {
        if (this.hayFraude()) {
            for (Observador observador : observadores) {
                observador.actualizar();
            }
            return;
        }
        viandas.remove(viandas.size() - 1);
    }

    private boolean hayFraude() {
        return true;
    }

    public int cantViandasDentro() {
        return viandas.size();
    }

    public int cantMesesActiva() {
        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(fechaInicioFuncionamiento, hoy);
        int cantMeses = periodo.getYears() * 12 + periodo.getMonths();
        return cantMeses;
    }

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void verificarTemp(Double temperatura) {
        if (temperatura < this.minTemp || temperatura > this.maxTemp) {
            this.estado = false;
        }
    }

    public static void main(String[] args) {
        // Connection con = ConexionBD.getConnection();
    }

}
