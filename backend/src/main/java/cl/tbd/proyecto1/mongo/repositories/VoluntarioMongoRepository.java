package cl.tbd.proyecto1.mongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators.In;

import cl.tbd.proyecto1.mongo.models.VoluntarioMongo;

public interface VoluntarioMongoRepository {
    public int countVoluntarios();
    public List<VoluntarioMongo> getVoluntarios();
    public VoluntarioMongo createVoluntario(VoluntarioMongo voluntario);
    public double promedioHabilidades();
    public VoluntarioMongo getVoluntario(Integer id);
    // public VoluntarioMongo updateVoluntario(VoluntarioMongo voluntario);
    public Integer deleteVoluntario(Integer id);
}