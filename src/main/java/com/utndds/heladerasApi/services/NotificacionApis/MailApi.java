package com.utndds.heladerasApi.services.NotificacionApis;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.UnsupportedEncodingException;

public class MailApi {
    public static void sendEmail(String subject, String message, String correo) {
        String host = "smtp.gmail.com"; // Servidor SMTP de Gmail

        // Obtener las credenciales del correo desde variables de entorno
        final String user = "hheladerasdds@gmail.com"; //MODIFICAR Y PONER EN VARIABLES DE ENTORNO
        final String password = "mhFWtC0X7UJ!0!oQ";//MODIFICAR Y PONER EN VARIABLES DE ENTORNO

        // Verificar que las credenciales no estén vacías
        if (user == null || password == null) {
            System.err.println("Las credenciales no están configuradas.");
            return;
        }

        // Configurar propiedades del servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Crear sesión autenticada
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            // Configurar el mensaje
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(user, "Admin Notificaciones")); // Remitente
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(correo)); // Destinatario
            msg.setSubject(subject);

            // Establecer el contenido del mensaje
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(message, "UTF-8"); // Contenido en texto plano

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart); // Agregar el contenido al correo

            msg.setContent(multipart);

            // Enviar el correo
            Transport.send(msg);
            System.out.println("Correo enviado con éxito a " + correo);

        } catch (MessagingException | UnsupportedEncodingException e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
