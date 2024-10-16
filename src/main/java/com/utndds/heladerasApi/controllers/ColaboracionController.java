package com.utndds.heladerasApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
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

    @PostMapping("/distribucion-viandas")
    public ResponseEntity<String> guardarDistribucionViandas(
            @RequestParam Long colaboradorId,
            @RequestBody DistribucionViandasDTO distribucionViandasDTO) {
        try {
            distribucionViandasService.guardarDistribucionViandas(colaboradorId, distribucionViandasDTO);
            return ResponseEntity.ok("Distribución de viandas guardada con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la distribución de viandas");
        }
    }

    @PostMapping("/donacion-dinero")
    public ResponseEntity<String> guardarDonacionDinero(
            @RequestParam double monto,
            @RequestParam Long colaboradorId) {
        try {
            donacionDineroService.guardarDonacionDinero(monto, colaboradorId);
            return ResponseEntity.ok("Donación de dinero guardada con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la donación de dinero");
        }
    }

    @PostMapping("/donacion-vianda")
    public ResponseEntity<String> guardarDonacionVianda(
            @RequestBody DonacionViandaDTO donacionViandaDTO,
            @RequestParam Long colaboradorId) {
        try {
            donacionViandaService.guardarDonacionVianda(donacionViandaDTO, colaboradorId);
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
            @RequestParam Long colaboradorId) {
        try {
            obtencionHeladeraService.registrarObtencionHeladera(direccion, colaboradorId);
            return ResponseEntity.ok("Obtención de heladera registrada con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar la obtención de heladera");
        }
    }

    @PostMapping("/oferta")
    public ResponseEntity<String> registrarOferta(
            @RequestBody OfertaDTO ofertaDTO,
            @RequestParam Long colaboradorId) {
        try {
            ofertaService.registrarOferta(ofertaDTO, colaboradorId);
            return ResponseEntity.ok("Oferta registrada con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar la oferta");
        }
    }

    @PostMapping("/persona-vulnerable")
    public ResponseEntity<String> crearPersonaVulnerable(
            @RequestBody PersonaVulnerableDTO personaDTO,
            @RequestParam Long colaboradorId) {
        try {
            registroPersVulnService.crearPersonaVulnerable(personaDTO, colaboradorId);
            return ResponseEntity.ok("Persona vulnerable registrada con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar la persona vulnerable");
        }
    }
}