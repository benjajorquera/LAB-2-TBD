package cl.tbd.proyecto1.repositories;

import java.util.List;
import cl.tbd.proyecto1.models.Tarea;

public interface TareaRepository {
    Tarea createTarea(Tarea tarea);
    List<Tarea> getAllTareas();
    Tarea getTarea(Integer id);
    Integer updateTarea(Tarea nuevaTarea);
    Boolean deleteTarea(Integer Id);
    List<Tarea> getTareasEmergencia(Integer id);
    Integer getCantVoluntariosEmergencia(Integer id);
}