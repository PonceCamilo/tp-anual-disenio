package com.utndds.heladerasApi.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConexionBD {

    public static Connection getConnection() {
        Connection conexion = null;
        String host = "localhost";
        String user = "root";
        String password = "";
        String db = "usersh";
        String url = String.format("jdbc:mysql://%s:3306/%s", host, db); // Corregido el formato de la URL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver: " + e.getMessage()); // Mensaje de error más claro
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage()); // Mensaje de error más
                                                                                             // claro
        }

        return conexion;
    }

    public static void insertarUsuarioHumano(Connection con, String nombre, String apellido, String fechaNacimiento,
            String colaboracion) {
        try {
            String query = "INSERT INTO phumanas (Nombre, Apellido, F_nac, Colaboración) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, fechaNacimiento);
            pstmt.setString(4, colaboracion);

            int filasInsertadas = pstmt.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Usuario insertado exitosamente");
            } else {
                System.out.println("No se pudo insertar el usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
        }
    }
    //PRUEBA PARA SABER QUE LA CONEXION FUNCIONA Y SE AGREGAN USUARIOS
    public static void main(String[] args) {
        Connection con = getConnection();
        if (con != null) {
            insertarUsuarioHumano(con, "John", "Doe", "1990-05-15", "Donacion Dinero");
            System.out.println("Usuario insertado correctamente");
        } else {
            System.out.println("Fallo al insertar usuario");
        }
    }
}
