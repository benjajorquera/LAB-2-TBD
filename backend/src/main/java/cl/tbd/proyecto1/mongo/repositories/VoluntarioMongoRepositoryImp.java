package cl.tbd.proyecto1.mongo.repositories;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cl.tbd.proyecto1.mongo.models.VoluntarioMongo;

@Repository
public class VoluntarioMongoRepositoryImp implements VoluntarioMongoRepository {

    @Autowired
    MongoDatabase database;

    @Override
    public int countVoluntarios() {
        MongoCollection<Document> collection = database.getCollection("voluntarios");
        long count = collection.countDocuments();
        return (int) count;
    }

    @Override
    public List<VoluntarioMongo> getVoluntarios() {
        MongoCollection<VoluntarioMongo> collection = database.getCollection("voluntarios", VoluntarioMongo.class);
        List <VoluntarioMongo> voluntarios = collection.find().into(new ArrayList<>());
        
        return voluntarios;
    }

    @Override
    public VoluntarioMongo createVoluntario(VoluntarioMongo voluntario) {
        MongoCollection<VoluntarioMongo> collection = database.getCollection("voluntarios", VoluntarioMongo.class);
        collection.insertOne(voluntario);
        return voluntario;
    }
    
}