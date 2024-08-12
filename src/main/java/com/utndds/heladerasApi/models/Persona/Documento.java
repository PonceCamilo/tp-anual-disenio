package com.utndds.heladerasApi.models.Persona;

public class Documento {
    String tipo;
    String numero;

    public Documento(String tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
