package cl.tbd.proyecto1.repositories;

import java.util.List;
import cl.tbd.proyecto1.models.Institucion;

public interface InstitucionRepository {

    public List<Institucion> getAllInstitucion();
    public Institucion getInstitucion(Integer id);
    public Institucion createInstitucion(Institucion institucion);
    public Integer updateInstitucion(Integer id, String nombreI, String descripI);
    public Integer deleteInstitucion(Integer id);
    
}