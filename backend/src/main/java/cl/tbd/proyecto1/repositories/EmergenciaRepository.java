package cl.tbd.proyecto1.repositories;

import java.util.List;
import cl.tbd.proyecto1.models.Emergencia;
import java.sql.Date;

public interface EmergenciaRepository {
    
    public List<Emergencia> getAllEmergencia();
    public Emergencia getEmergencia(Integer id);
    public Emergencia createEmergencia(Emergencia emergencia);
    public Integer updateEmergencia(Integer id, String nombreE, String descripE, Date finicioE, Date ffinE, Integer id_institucionE);
    public Integer deleteEmergencia(Integer id);
}