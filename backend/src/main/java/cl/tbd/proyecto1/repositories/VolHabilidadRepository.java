package cl.tbd.proyecto1.repositories;

import java.util.List;
import cl.tbd.proyecto1.models.VolHabilidad;

public interface VolHabilidadRepository {
    
    public List<VolHabilidad> getAllVolHabilidad();
    public VolHabilidad getVolHabilidad(Integer id);
    public VolHabilidad createVolHabilidad(VolHabilidad volHabilidad);
    public Integer updateVolHabilidad(Integer id, Integer id_vol, Integer id_hab);
    public Integer deleteVolHabilidad(Integer id);
}