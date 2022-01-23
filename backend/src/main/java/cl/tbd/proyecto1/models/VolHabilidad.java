package cl.tbd.proyecto1.models;

public class VolHabilidad {
    
    private Integer id;
    private Integer id_voluntario;
    private Integer id_habilidad;

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id= id;
    }

    public Integer getIdVoluntario(){
        return this.id_voluntario;
    }

    public void setIdVoluntario(Integer id_voluntario){
        this.id_voluntario= id_voluntario;
    }

    public Integer getIdHabilidad(){
        return this.id_habilidad;
    }

    public void setIdHabilidad(Integer id_habilidad){
        this.id_habilidad= id_habilidad;
    }
}
