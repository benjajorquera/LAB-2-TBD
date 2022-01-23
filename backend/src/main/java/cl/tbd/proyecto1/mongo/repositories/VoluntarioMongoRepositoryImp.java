package cl.tbd.proyecto1.mongo.repositories;

import org.bson.BsonNull;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public double promedioHabilidades() {
        MongoCollection<Document> collection = database.getCollection("voluntarios");
        double count = collection.countDocuments();
        List<Bson> habilidades = Arrays.asList(new Document("$group", new Document("_id", new BsonNull()).append("habilidadesCount", new Document("$sum", new Document("$size", "$habilidades")))));
        List<Document> output = collection.aggregate(habilidades).into(new ArrayList<Document>());
        Integer cantidadHabilidades = (Integer) output.get(0).get("habilidadesCount");
        double resultado = (double) Math.round(cantidadHabilidades * 100 / count) / 100;
        return resultado;
    }
    
}