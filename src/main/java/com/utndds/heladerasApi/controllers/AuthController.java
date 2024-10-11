package com.utndds.heladerasApi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RestController
public class AuthController {

    @Value("${okta.oauth2.client-id}")
    private String clientId;

    @Value("${okta.oauth2.client-secret}")
    private String clientSecret;

    @Value("${okta.oauth2.redirect-uri}")
    private String redirectUri;

    @Value("${okta.oauth2.issuer}")
    private String issuer;

    private final RestTemplate restTemplate;

    public AuthController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/callback")
    public ResponseEntity<?> handleCallback(@RequestParam("code") String code) {

        // URL para intercambiar el código de autorización por el token de acceso
        String tokenUrl = issuer + "/oauth/token";

        // Crear los headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Crear el cuerpo de la solicitud
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("code", code);
        requestBody.add("redirect_uri", redirectUri);
        // Crear la entidad de la solicitud
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            // Intercambiar el código por el token de acceso
            ResponseEntity<String> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, requestEntity,
                    String.class);
            String responseBody = response.getBody();
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            // Capturar cualquier excepción que ocurra
            System.out.println("Error al intercambiar el código por el token: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al intercambiar el código por el token");
        }
    }
}
