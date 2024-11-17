package com.utndds.heladerasApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.utndds.heladerasApi.services.ReconocimientosExtra;
import com.utndds.heladerasApi.services.Colaboraciones.DistribucionViandasService;
import com.utndds.heladerasApi.services.Colaboraciones.DonacionDineroService;
import com.utndds.heladerasApi.services.Colaboraciones.DonacionViandaService;
import com.utndds.heladerasApi.services.Colaboraciones.ObtencionHeladeraService;
import com.utndds.heladerasApi.services.Colaboraciones.OfertaService;
import com.utndds.heladerasApi.services.Colaboraciones.RegistroPersVulnService;

import jakarta.persistence.EntityNotFoundException;

import com.utndds.heladerasApi.DTOs.DistribucionViandasDTO;
import com.utndds.heladerasApi.DTOs.DonacionViandaDTO;
import com.utndds.heladerasApi.DTOs.OfertaDTO;
import com.utndds.heladerasApi.DTOs.PersonaVulnerableDTO;
import com.utndds.heladerasApi.models.Rol.Colaborador;

@RestController
@RequestMapping("/colaboraciones")
public class ColaboracionController {

    @Autowired
    private ReconocimientosExtra reconocimientosExtra;

    @Autowired
    private DistribucionViandasService distribucionViandasService;

    @Autowired
    private DonacionDineroService donacionDineroService;

    @Autowired
    private DonacionViandaService donacionViandaService;

    @Autowired
    private ObtencionHeladeraService obtencionHeladeraService;

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private RegistroPersVulnService registroPersVulnService; // Add RegistroPersVulnService

    @GetMapping("/recomendaciones-colaboradores")
    public ResponseEntity<List<Colaborador>> recomendarColaboradores(@RequestParam double puntosReq,
            @RequestParam double viandasDonadasReq, @RequestParam int cantMaxColabs) {
        try {
            List<Colaborador> colaboradores = reconocimientosExtra.recomendarColaboradores(puntosReq, viandasDonadasReq,
                    cantMaxColabs);
            return ResponseEntity.ok(colaboradores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_COLLABORATOR')")
    @PostMapping("/distribucion-viandas")
    public ResponseEntity<String> guardarDistribucionViandas(
            @RequestParam String colaboradorUUID,
            @RequestBody DistribucionViandasDTO distribucionViandasDTO) {
        try {
            distribucionViandasService.guardarDistribucionViandas(colaboradorUUID, distribucionViandasDTO);
            return ResponseEntity.ok("Distribución de viandas guardada con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la distribución de viandas");
        }
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_COLLABORATOR')")
    @PostMapping("/donacion-dinero")
    public ResponseEntity<String> guardarDonacionDinero(
            @RequestParam double monto,
            @RequestParam String colaboradorUUID) {
        try {
            donacionDineroService.guardarDonacionDinero(monto, colaboradorUUID);
            return ResponseEntity.ok("Donación de dinero guardada con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la donación de dinero");
        }
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_COLLABORATOR')")
    @PostMapping("/donacion-vianda")
    public ResponseEntity<String> guardarDonacionVianda(
            @RequestBody DonacionViandaDTO donacionViandaDTO,
            @RequestParam String colaboradorUUID) {
        try {

            donacionViandaService.guardarDonacionVianda(donacionViandaDTO, colaboradorUUID);
            return ResponseEntity.ok("Donación de vianda guardada con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la donación de vianda");
        }
    }

    @PostMapping("/obtencion-heladera")
    public ResponseEntity<String> registrarObtencionHeladera(
            @RequestParam String direccion,
            @RequestParam String colaboradorUUID) {
        System.out.println("el colaboradorUUID es: " + colaboradorUUID);
        System.out.println("la direccion es: " + direccion);
        try {
            obtencionHeladeraService.registrarObtencionHeladera(direccion, colaboradorUUID);
            return ResponseEntity.ok("Obtención de heladera registrada con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar la obtención de heladera");
        }
    }

    @PostMapping("/oferta")
    public ResponseEntity<Map<String, String>> registrarOferta(
            @RequestBody OfertaDTO ofertaDTO,
            @RequestParam String colaboradorUUID) {
        try {
            ofertaService.registrarOferta(ofertaDTO, colaboradorUUID);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Oferta registrada con éxito");
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al registrar la oferta"));
        }
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_COLLABORATOR')")
    @PostMapping("/persona-vulnerable")
    public ResponseEntity<String> crearPersonaVulnerable(
            @RequestBody PersonaVulnerableDTO personaDTO,
            @RequestParam String colaboradorUUID) {
        try {
            registroPersVulnService.crearPersonaVulnerable(personaDTO, colaboradorUUID);
            return ResponseEntity.ok("Persona vulnerable registrada con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar la persona vulnerable");
        }
    }
}