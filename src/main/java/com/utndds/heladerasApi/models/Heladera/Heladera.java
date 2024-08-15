package com.utndds.heladerasApi.models.Heladera;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente;
import com.utndds.heladerasApi.models.Heladera.Sensores.SensorMovimiento;
import com.utndds.heladerasApi.models.ONG.ONG;
import com.utndds.heladerasApi.models.Observer.ObservadorHeladera;
import com.utndds.heladerasApi.models.Suscripciones.Suscripcion;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "Heladera")
public class Heladera implements ObservadorHeladera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "ong", referencedColumnName = "id")
    private ONG ong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "punto")
    private Punto punto;

    @Column(name = "capacidad")
    int capacidad;

    @Column(name = "fecha_inicio_funcionamiento")
    LocalDate fechaInicioFuncionamiento;

    @Column(name = "funcionando")
    boolean funcionando;

    @Column(name = "abierta")
    boolean abierta;

    @OneToOne(mappedBy = "heladera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    SensorMovimiento sensorMov;

    @OneToOne(mappedBy = "heladera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    ManejadorTemperatura manejadorTemperatura;

    @OneToMany(mappedBy = "heladera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Vianda> viandas = new ArrayList<>();

    @OneToMany(mappedBy = "heladera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Suscripcion> suscriptores = new ArrayList<>();

    @OneToMany(mappedBy = "heladera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Incidente> incidentes = new ArrayList<>();

    public Heladera(ONG ong, Punto punto, int capacidad, double minTemp, double maxTemp, boolean funcionando,
            boolean abierta, LocalDate fechaInicioFuncionamiento, List<Vianda> viandas) {
        this.ong = ong;
        this.punto = punto;
        this.capacidad = capacidad;
        this.funcionando = funcionando;
        this.abierta = abierta;
        this.fechaInicioFuncionamiento = fechaInicioFuncionamiento;
        this.viandas = viandas;
        this.manejadorTemperatura = new ManejadorTemperatura(this, minTemp, maxTemp);
        this.sensorMov = new SensorMovimiento(this);
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

    public void agregarVianda(Vianda vianda) {
        this.viandas.add(vianda);
    }

    public boolean estaFuncionando() {
        return this.funcionando;
    }

}
