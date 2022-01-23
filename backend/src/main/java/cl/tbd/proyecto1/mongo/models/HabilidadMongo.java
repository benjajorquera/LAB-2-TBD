package cl.tbd.proyecto1.mongo.models;

import org.bson.types.ObjectId;

import org.bson.codecs.pojo.annotations.BsonId;

public class HabilidadMongo {
    @BsonId
    private ObjectId _id;
    private String codigo;
    private String descrip;
    
    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    /*public String getStringId(){
        return this._id.toString();
    }*/

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
