package cl.tbd.proyecto1.models;

import java.sql.Date;

public class EmergenciaFinalizada {

    private Integer id;
    private String nombre;
    private String descrip;
    private Date finicio;
    private Date ffin;
    private Integer id_institucion;
    private Integer total_tareas;
    private Integer total_voluntarios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Date getFinicio() {
        return finicio;
    }

    public void setFinicio(Date finicio) {
        this.finicio = finicio;
    }

    public Date getFfin() {
        return ffin;
    }
        
    public void setFfin(Date ffin) {
        this.ffin = ffin;
    }

    public Integer getId_institucion() {
        return id_institucion;
    }

    public void setId_institucion(Integer id_institucion) {
        this.id_institucion = id_institucion;
    }

    // getters and setters for total_tareas and total_voluntarios
    public Integer getTotal_tareas() {
        return total_tareas;
    }

    public void setTotal_tareas(Integer total_tareas) {
        this.total_tareas = total_tareas;
    }

    public Integer getTotal_voluntarios() {
        return total_voluntarios;
    }

    public void setTotal_voluntarios(Integer total_voluntarios) {
        this.total_voluntarios = total_voluntarios;
    }

    // Constructor
    public EmergenciaFinalizada(Integer id, String nombre, String descrip, Date finicio, Date ffin, Integer id_institucion, Integer total_tareas, Integer total_voluntarios) {
        this.id = id;
        this.nombre = nombre;
        this.descrip = descrip;
        this.finicio = finicio;
        this.ffin = ffin;
        this.id_institucion = id_institucion;
        this.total_tareas = total_tareas;
        this.total_voluntarios = total_voluntarios;
    }
}
