package com.utndds.heladerasApi.models.Rol.Contacto;

public class Email extends Contacto {
    String email;

    public Email(String email) {
        this.email = email;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("SE NOTIFICO CON EXITO AL EMAIL: " + this.email);
    }
}
