package com.utndds.heladerasApi.models.Sistema;

import java.util.List;
import java.util.ArrayList;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.SolicitudApertura;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.Tecnico;

public class Sistema {
    private static volatile Sistema instancia;
    private List<Heladera> heladeras;
    private List<Colaborador> colaboradores;
    private List<Tecnico> tecnicos;
    private List<SolicitudApertura> solicitudes;

    // Constructor privado para evitar la instanciación directa
    private Sistema() {
        this.heladeras = new ArrayList<>();
        this.colaboradores = new ArrayList<>();
        this.tecnicos = new ArrayList<>();
        this.solicitudes = new ArrayList<>();
    }

    // Método público para obtener la instancia única
    public static Sistema getInstance() {
        if (instancia == null) {
            synchronized (Sistema.class) {
                if (instancia == null) {
                    instancia = new Sistema();
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