package com.utndds.heladerasApi.models.ONG;

import java.util.List;
import java.util.ArrayList;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.Tecnico;
import com.utndds.heladerasApi.models.Solicitudes.SolicitudApertura;
import jakarta.persistence.*;

@Entity
@Table(name = "ong")
public class ONG {
    private static volatile ONG instancia;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Heladera> heladeras;

    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Colaborador> colaboradores;

    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tecnico> tecnicos;

    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SolicitudApertura> solicitudes;

    private ONG() {
        this.heladeras = new ArrayList<>();
        this.colaboradores = new ArrayList<>();
        this.tecnicos = new ArrayList<>();
        this.solicitudes = new ArrayList<>();
    }

    // Método público para obtener la instancia única
    public static ONG getInstance() {
        if (instancia == null) {
            synchronized (ONG.class) {
                if (instancia == null) {
                    instancia = new ONG();
                }
            }
        }
        return instancia;
    }

    public Colaborador buscarColaborador(String tipoDoc, String numeroDoc) {
        for (Colaborador colaborador : colaboradores) {
            PersonaHumana persona = (PersonaHumana) colaborador.getPersona();
            if (persona.getDocumento().getTipo().equals(tipoDoc)
                    && persona.getDocumento().getNumero().equals(numeroDoc)) {
                return colaborador;
            }
        }
        return null;
    }

    public void agregarColaborador(Colaborador colaborador) {
        this.colaboradores.add(colaborador);
    }

    public void agregarSolicitud(SolicitudApertura solicitud) {
        this.solicitudes.add(solicitud);
    }

    public List<Colaborador> getColaboradores() {
        return this.colaboradores;
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public List<Heladera> getHeladeras() {
        return heladeras;
    }

    public List<SolicitudApertura> getSolicitudes() {
        return solicitudes;
    }

}