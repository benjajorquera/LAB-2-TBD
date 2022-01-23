package cl.tbd.proyecto1.mongo.repositories;

import java.util.List;

import cl.tbd.proyecto1.mongo.models.Dog;

public interface DogRepository {
    public List<Dog> getDogs();
}