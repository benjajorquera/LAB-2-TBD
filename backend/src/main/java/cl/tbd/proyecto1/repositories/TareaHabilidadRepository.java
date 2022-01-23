package cl.tbd.proyecto1.repositories;
import java.util.List;
import cl.tbd.proyecto1.models.TareaHabilidad;

public interface TareaHabilidadRepository {
 
    List<TareaHabilidad> getAllTareaHabilidad();
    TareaHabilidad getTareaHabilidad(Integer id);
    TareaHabilidad createTareaHabilidad(TareaHabilidad tareaHabilidad);
    Integer updateTareaHabilidad(Integer id, Integer id_emehab, Integer id_tarea);
    boolean deleteTareaHabilidad(Integer id);
}
