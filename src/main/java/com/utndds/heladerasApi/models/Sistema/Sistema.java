package com.utndds.heladerasApi.models.Sistema;

import java.util.List;
import java.util.ArrayList;

import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.services.CalculadoraPuntosService;

public class Sistema {
    private static volatile Sistema instancia;
    private List<Colaborador> colaboradores;
    private CalculadoraPuntosService calculadoraPuntos = new CalculadoraPuntosService();

    // Constructor privado para evitar la instanciación directa
    private Sistema() {
        this.colaboradores = new ArrayList<>();
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

    public double calcularPuntos(Colaborador colaborador) {
        return this.calculadoraPuntos.calcularPuntos(colaborador);
    }

    public List<Colaborador> getColaboradores() {
        return this.colaboradores;
    }

}