package cl.tbd.proyecto1.mongo.repositories;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cl.tbd.proyecto1.mongo.models.HabilidadMongo;

@Repository
public class HabilidadMongoRepositoryImp implements HabilidadMongoRepository {

    @Autowired
    MongoDatabase database;

    @Override
    public int countHabilidades() {
        MongoCollection<Document> collection = database.getCollection("habilidad");
        long count = collection.countDocuments();
        return (int) count;
    }

    @Override
    public List<HabilidadMongo> getHabilidades() {
        MongoCollection<HabilidadMongo> collection = database.getCollection("habilidad", HabilidadMongo.class);
        List <HabilidadMongo> habilidades = collection.find().into(new ArrayList<>());
        
        return habilidades;
    }

    @Override
    public HabilidadMongo createHabilidad(HabilidadMongo habilidad) {
        MongoCollection<HabilidadMongo> collection = database.getCollection("habilidad", HabilidadMongo.class);
        collection.insertOne(habilidad);
        return habilidad;
    }
    
}