package cl.tbd.proyecto1.mongo.models;

import org.bson.types.ObjectId;
import org.bson.codecs.pojo.annotations.BsonId;

public class Dog {
    @BsonId
    private ObjectId _id;
    private String name;
    
    public ObjectId get_id() {
        return _id;
    }
    public void set_id(ObjectId _id) {
        this._id = _id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
}
