package cl.tbd.proyecto1.repositories;

import java.util.List;
import java.sql.Date;
import cl.tbd.proyecto1.models.Voluntario;

public interface VoluntarioRepository {
    
    public List<Voluntario> getAllVoluntario();
    public Voluntario getVoluntario(Integer id);
    public Voluntario createVoluntario(Voluntario voluntario);
    public Integer updateVoluntario (Integer id, String nombre, Date fNacimiento);
    public Integer deleteVoluntario(Integer id);
}
