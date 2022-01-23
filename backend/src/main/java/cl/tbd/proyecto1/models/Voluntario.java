package cl.tbd.proyecto1.models;

import java.sql.Date;

public class Voluntario {
    
    private Integer id;
    private String nombre;
    private Date fnacimiento;

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

    public Date getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
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
