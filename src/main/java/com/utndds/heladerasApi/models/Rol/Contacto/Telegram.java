package com.utndds.heladerasApi.models.Rol.Contacto;

public class Telegram extends Contacto {
    String numero;

    public Telegram(String numero) {
        this.numero = numero;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("SE NOTIFICO CON EXITO AL TELEGRAM: " + this.numero);
    }
}
