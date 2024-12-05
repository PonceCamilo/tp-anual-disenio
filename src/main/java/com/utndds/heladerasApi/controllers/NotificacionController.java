package com.utndds.heladerasApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utndds.heladerasApi.services.NotificacionService;

@RestController
@RequestMapping("/notificacion")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    public NotificacionController() {
        this.notificacionService = new NotificacionService();

    }

    @PostMapping("/enviarNotificacion")
    public void sendNotification(@RequestParam("mensaje") String message) {
        notificacionService.sendNotification(message);
    }

}
