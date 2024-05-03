package com.utndds.heladerasApi.models.Colaboradores;
import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonaHumana extends Colaborador {
    String nombre;
    String apellido;
    Date fechaDeNacimiento;
    List<Colaboracion> posiblesColaboraciones = new ArrayList<Colaboracion>();

    public PersonaHumana(String direccion, String nombre, String apellido, Date fechaDeNacimiento) {
        super(direccion);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
}
