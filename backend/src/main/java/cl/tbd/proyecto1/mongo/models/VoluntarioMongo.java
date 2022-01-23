package cl.tbd.proyecto1.mongo.models;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;

public class VoluntarioMongo {
    @BsonId
    private ObjectId _id;
    private String nombre;
    private Date fnacimiento;
    private List<String> habilidades;
    
    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    /*public String getStringId(){
        return this._id.toString();
    }*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFNacimiento() {
        return fnacimiento;
    }

    public void setFNacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }
    
    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }
}
