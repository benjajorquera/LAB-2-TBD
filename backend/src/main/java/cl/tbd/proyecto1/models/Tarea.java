package cl.tbd.proyecto1.models;

import java.sql.Date;

public class Tarea {

    private Integer id;
    private String nombre;
    private String descrip;
    private Integer cant_vol_requeridos;
    private Integer cant_vol_inscritos;
    private Integer id_emergencia;
    private Date finicio;
    private Date ffin;
    private Integer id_estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Integer getCantVolRequeridos() {
        return cant_vol_requeridos;
    }

    public void setCantVolRequeridos(Integer cant_vol_requeridos) {
        this.cant_vol_requeridos = cant_vol_requeridos;
    }

    public Integer getCantVolInscritos() {
        return cant_vol_inscritos;
    }

    public void setCantVolInscritos(Integer cant_vol_inscritos) {
        this.cant_vol_inscritos = cant_vol_inscritos;
    }

    public Integer getIdEmergencia() {
        return id_emergencia;
    }

    public void setIdEmergencia(Integer id_emergencia) {
        this.id_emergencia = id_emergencia;
    }

    public Date getFInicio() {
        return finicio;
    }

    public void setFInicio(Date finicio) {
        this.finicio = finicio;
    }

    public Date getFFin() {
        return ffin;
    }

    public void setFFin(Date ffin) {
        this.ffin = ffin;
    }

    public Integer getIdEstado() {
        return id_estado;
    }

    public void setIdEstado(Integer id_estado) {
        this.id_estado = id_estado;
    }

}
