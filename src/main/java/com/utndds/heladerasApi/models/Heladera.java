package com.utndds.heladerasApi.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Heladera {
    String nombre;
    double longitud;
    double latitud;
    String direccion;
    LocalDate fechaInicioFuncionamiento;
    List<Vianda> viandas = new ArrayList<>();

    public Heladera(String nombre, double longitud, double latitud, String direccion,
            LocalDate fechaInicioFuncionamiento, List<Vianda> viandas) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
        this.direccion = direccion;
        this.fechaInicioFuncionamiento = fechaInicioFuncionamiento;
        this.viandas = viandas;
    }
    public int cantViandasDentro() {
        return viandas.size();
    }

    public void Alta(Connection con) {
        try {
            String query = "INSERT INTO heladeras (Nombre, Longitud, Latitud, Direccion, F_funcionamiento, Cant_viandas) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, longitud);
            pstmt.setDouble(3, latitud);
            pstmt.setString(4, direccion);
            pstmt.setDate(5, java.sql.Date.valueOf(fechaInicioFuncionamiento));
            pstmt.setString(6, String.valueOf(cantViandasDentro()));
            
            int filasInsertadas = pstmt.executeUpdate();
            
            if (filasInsertadas > 0) {
                System.out.println("Heladera dada de alta exitosamente");
            } else {
                System.out.println("No se pudo dar de alta la heladera");
            }
        } catch(SQLException e) {
            System.out.println("Error al insertar Heladera: " + e.getMessage());
        }
    }

        
        public static void main(String[] args) {
            Connection con = ConexionBD.getConnection();
            Heladera heladera = new Heladera("UTN Heladera", -34.6037, -58.3816, "Medrano 951", LocalDate.now(), new ArrayList<>());
            heladera.Alta(con);
        }
    
}
