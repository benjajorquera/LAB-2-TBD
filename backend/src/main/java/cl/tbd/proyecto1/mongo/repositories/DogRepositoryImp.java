package cl.tbd.proyecto1.mongo.repositories;

import java.util.List;
import java.util.ArrayList;

import cl.tbd.proyecto1.mongo.models.Dog;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DogRepositoryImp implements DogRepository {
    @Autowired
    MongoDatabase database;

    @Override
    public List<Dog> getDogs() {
        MongoCollection<Dog> dogsCollection = database.getCollection("dogs", Dog.class);
        List<Dog> dogsList = dogsCollection.find().into(new ArrayList<>());
        return dogsList;
    }
    
}
