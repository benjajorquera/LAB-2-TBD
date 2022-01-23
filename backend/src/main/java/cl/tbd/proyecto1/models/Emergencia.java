package cl.tbd.proyecto1.models;

import java.sql.Date;

public class Emergencia {

    private Integer id;
    private String nombre;
    private String descrip;
    private Date finicio;
    private Date ffin;
    private Integer id_institucion;
    
    // postgis
    private double longitude;
    private double latitude;

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

    // postgis
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
