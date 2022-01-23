package cl.tbd.proyecto1.repositories;

import java.util.List;
import cl.tbd.proyecto1.models.Habilidad;

public interface HabilidadRepository {
    public Habilidad getHabilidad(Integer id);
    public List<Habilidad> getAllHabilidades();
    public Habilidad createHabilidad(Habilidad habilidad);
    public Integer updateHabilidad(Integer id, String descrip);
    public Integer deleteHabilidad(Integer id);
}
