package cl.tbd.proyecto1.services;

import cl.tbd.proyecto1.models.EmergenciaFinalizada;

import cl.tbd.proyecto1.models.Emergencia;
import cl.tbd.proyecto1.repositories.EmergenciaRepository;

import cl.tbd.proyecto1.repositories.TareaRepository;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class VueService {

    private final EmergenciaRepository emergenciaRepository;
    private final TareaRepository tareaRepository;

    VueService(EmergenciaRepository emergenciaRepository, TareaRepository tareaRepository) {
        this.emergenciaRepository = emergenciaRepository;
        this.tareaRepository = tareaRepository;
    }

    @GetMapping("/emergencias/finalizadas")
    @ResponseBody
    public List<EmergenciaFinalizada> getAllEmergenciasFinalizadas() {
        // Crear lista de emergencias finalizadas
        List<EmergenciaFinalizada> emergenciasFinalizadas = new ArrayList<EmergenciaFinalizada>();
        
        // obtener todas las emergencias
        List<Emergencia> emergencias = emergenciaRepository.getAllEmergencia();
        
        // obtener todas las tareas y las tareas finalizadas
        for(Emergencia temp : emergencias) {
            int totalTareas = tareaRepository.getTotalTareasEmergencia(temp.getId());
            int totalFinalizadas = tareaRepository.getTotalTareasFinalizadasEmergencia(temp.getId());

            System.out.println("\n\n\n");
            System.out.println("Emergencia: " + temp.getId());
            System.out.println("Total tareas: " + totalTareas);
            System.out.println("Total tareas finalizadas: " + totalFinalizadas);
            System.out.println("\n\n\n");

            // comparar si la cantidad de tareas finalizadas es igual a la cantidad de tareas totales y que el total de tareas no sea 0
            if(totalTareas ==  totalFinalizadas && totalTareas != 0) {
                // obtener el total de voluntarios inscritos en la emergencia
                int totalVoluntarios = tareaRepository.getCantVoluntariosEmergencia(temp.getId());
                
                // añadir a la lista de emergencias finalizadas emergencia + total de voluntarios inscritos + tareas finalizadas
                // EmergenciaFinalizada(Integer id, String nombre, String descrip, Date finicio, Date ffin, Integer id_institucion, Integer total_tareas, Integer total_voluntarios)
                EmergenciaFinalizada emergenciaFinalizada = new EmergenciaFinalizada(
                    temp.getId(), temp.getName(), temp.getDescrip(), temp.getFinicio(), 
                    temp.getFfin(), temp.getId_institucion(), totalTareas, totalVoluntarios
                    );
                emergenciasFinalizadas.add(emergenciaFinalizada);
            }

        }

        // return emergencias finalizadas
        return emergenciasFinalizadas;
    }
}

