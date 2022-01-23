package cl.tbd.proyecto1.repositories;

import java.util.List;
import cl.tbd.proyecto1.models.EmeHabilidad;

public interface EmeHabilidadRepository {
    public EmeHabilidad getEmeHabilidad(Integer id);
    public List<EmeHabilidad> getAllEmeHabilidad();
    public EmeHabilidad createEmeHabilidad(EmeHabilidad emeHabilidad);
    public Integer updateEmeHabilidad (Integer id, Integer idEme, Integer idHab);
    public Integer deleteEmeHabilidad(Integer id);
}
