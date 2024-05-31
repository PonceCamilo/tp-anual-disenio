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

    public void alta(Connection con) {
        try {
            String query = "INSERT INTO heladeras (Nombre, Longitud, Latitud, Direccion, F_funcionamiento, Cant_viandas) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, longitud);
            pstmt.setDouble(3, latitud);
            pstmt.setString(4, direccion);
            pstmt.setDate(5, java.sql.Date.valueOf(fechaInicioFuncionamiento));
            pstmt.setString(6, String.valueOf(cantViandasDentro()));

            int filasInsertadas = pstmt.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Heladera dada de alta exitosamente");
            } else {
                System.out.println("No se pudo dar de alta la heladera");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar Heladera: " + e.getMessage());
        }
    }

    public void baja() {
        System.out.println("Se dio de baja la heladera: " + this.nombre);
    }

    public void modificar() {
        System.out.println("Se modifico la heladera: " + this.nombre);
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

    public int cantViandasDentro() {
        return viandas.size();
    }

    private boolean hayFraude() {
        return true;
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

    public static void main(String[] args) {
        // Connection con = ConexionBD.getConnection();
    }

}
