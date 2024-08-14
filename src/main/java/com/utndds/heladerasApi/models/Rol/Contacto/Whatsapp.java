package com.utndds.heladerasApi.models.Rol.Contacto;

public class Whatsapp extends Contacto {
    String numero;

    public Whatsapp(String numero) {
        this.numero = numero;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("SE NOTIFICO CON EXITO AL WHATSAPP: " + this.numero);
    }
}
