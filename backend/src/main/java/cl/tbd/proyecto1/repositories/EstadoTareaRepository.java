package cl.tbd.proyecto1.repositories;

import java.util.List;
import cl.tbd.proyecto1.models.EstadoTarea;

public interface EstadoTareaRepository {
    public List<EstadoTarea> getAllEstadosTareas();
    public EstadoTarea getEstadoTarea(Integer id);
    public EstadoTarea createEstadoTarea(EstadoTarea estadoTarea);
    public Integer deleteEstadoTarea(Integer Id);
    Integer updateEstadoTarea(EstadoTarea nuevoEstadoTarea);
}
