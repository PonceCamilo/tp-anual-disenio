package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Colaboraciones.DonacionVianda;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ConocerColaboracionesService {

    public int getViandasDonadas(Colaborador colaborador, int dias) {
        int viandasDonadas = 0;
        LocalDate fechaInicio = LocalDate.now().minusDays(dias); // Define el inicio del rango de tiempo

        List<Colaboracion> colaboraciones = colaborador.getColaboraciones();
        for (Colaboracion colaboracion : colaboraciones) {
            if (colaboracion instanceof DonacionVianda) {
                DonacionVianda donacion = (DonacionVianda) colaboracion;
                if (donacion.getFecha().isAfter(fechaInicio)) {
                    viandasDonadas++;
                }
            }
        }
        return viandasDonadas;
    }

    
}
