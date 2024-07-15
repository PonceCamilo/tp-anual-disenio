package com.utndds.heladerasApi.models.Heladera;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Rol.Colaborador;

public class Heladera implements Observador {
    String nombre;
    Ubicacion ubicacion;
    int capacidad;
    String direccion;
    double ultimaTempRegistrada;
    double minTemp;
    double maxTemp;
    boolean estado;
    List<Observador> observadores = new ArrayList<>();
    List<SolicitudApertura> solicitudes = new ArrayList<>();
    LocalDate fechaInicioFuncionamiento;
    List<Vianda> viandas = new ArrayList<>();

    public Heladera(String nombre, Ubicacion ubicacion, int capacidad, String direccion, double ultimaTempRegistrada,
            double minTemp, double maxTemp, boolean estado, LocalDate fechaInicioFuncionamiento, List<Vianda> viandas,
            List<SolicitudApertura> solicitudes) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.direccion = direccion;
        this.ultimaTempRegistrada = ultimaTempRegistrada;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.estado = estado;
        this.fechaInicioFuncionamiento = fechaInicioFuncionamiento;
        this.viandas = viandas;
        this.solicitudes = solicitudes;
    }

    public void setUltimaTempRegistrada(double ultimaTempRegistrada) {
        this.ultimaTempRegistrada = ultimaTempRegistrada;
    }

    public String getNombre() {
        return nombre;
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

    @Override
    public void actualizar() {
        if (this.ultimaTempRegistrada < this.minTemp || this.ultimaTempRegistrada > this.maxTemp) {
            this.estado = false;
        }
    }

    public void agregarSolicitud(SolicitudApertura solicitud) {
        this.solicitudes.add(solicitud);
    }

    public void eliminarSolicitud(SolicitudApertura solicitud) {
        this.solicitudes.remove(solicitud);
    }

    public void extraerVianda(Vianda vianda) {
        if (this.hayFraude()) {
            for (Observador observador : observadores) {
                observador.actualizar();
            }
            return;
        }
        viandas.remove(vianda);
    }

    private boolean hayFraude() {
        return true;
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

    public void notificarDesperfecto() {
    }

    public static void main(String[] args) {
        // Connection con = ConexionBD.getConnection();
    }

}
