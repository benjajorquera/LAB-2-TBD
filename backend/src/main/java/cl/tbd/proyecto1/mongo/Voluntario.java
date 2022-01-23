package cl.tbd.proyecto1.mongo;
import org.springframework.data.annotation.Id;
import java.sql.Date;
import java.util.List;

public class Voluntario{
    
    @Id private String id;
    private String nombre;
    private Date fnacimiento;
    private List<String> habilidades;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFNacimiento() {
        return this.fnacimiento;
    }

    public void setFNacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public List<String> getHabilidades() {
        return this.habilidades;
    }

    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }

    public Voluntario(String nombre, Date fnacimiento, List<String> habilidades){
        this.nombre = nombre;
        this.fnacimiento = fnacimiento;
        this.habilidades = habilidades;
    }
}