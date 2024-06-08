package com.utndds.heladerasApi.models.CargaCSV;
import java.time.LocalDate;
import java.util.Arrays;
import com.utndds.heladerasApi.models.Persona.Colaboradores.PersonaHumana;
import com.utndds.heladerasApi.models.Persona.Contacto.Email;
import com.utndds.heladerasApi.models.Colaboraciones.DonacionDinero;
import com.utndds.heladerasApi.models.Colaboraciones.DonacionVianda;
import com.utndds.heladerasApi.models.Colaboraciones.DistribucionViandas;
import com.utndds.heladerasApi.models.Colaboraciones.RegistroPersonaVulnerable;
import com.utndds.heladerasApi.models.Heladera.Vianda;


public class PersonaHumanaFactory {
    public PersonaHumana crearPersonaHumana(String[] registro) {
        Email email = new Email(registro[4]);
        PersonaHumana persona = new PersonaHumana();
            persona.setNombre(registro[2]);
            persona.setApellido(registro[3]);  
            persona.setMediosDeContacto(Arrays.asList(email));
            String tipoColaboracion = registro[5];
            switch (tipoColaboracion) {
                case "DINERO":
                double monto = Double.parseDouble(registro[6]);               
                DonacionDinero dinero = new DonacionDinero(LocalDate.now(), persona, monto,0);
                persona.realizarColaboracion(dinero);
                    break;
                case "DONACION_VIANDAS":
                int cantidadViandas = Integer.parseInt(registro[6]);
                for(int i = 0;i <cantidadViandas;i++){
                Vianda viandaDonada = new Vianda("Desconocido", LocalDate.now(), 0, 0); // Valores predeterminados // Valor predeterminado
                boolean estado = true; 
                DonacionVianda donacionVianda = new DonacionVianda(LocalDate.now(), persona, viandaDonada, null, estado);
                persona.realizarColaboracion(donacionVianda);
                } 
                    break;
                case "REDISTRIBUCION_VIANDAS":
                int redistribucion = Integer.parseInt(registro[6]); 
                DistribucionViandas redistribucionVianda = new DistribucionViandas(LocalDate.now(), persona, null, null,redistribucion,null);
                persona.realizarColaboracion(redistribucionVianda);
                    break;
                case "ENTREGA_TARJETAS":
                int cantidadTarjetas = Integer.parseInt(registro[6]);
                for(int t = 0;t < cantidadTarjetas;t++){
                RegistroPersonaVulnerable registrar = new RegistroPersonaVulnerable(LocalDate.now(), persona, null);
                persona.realizarColaboracion(registrar);   
                }
                break;
            }
            
            return persona;
        }  
}
