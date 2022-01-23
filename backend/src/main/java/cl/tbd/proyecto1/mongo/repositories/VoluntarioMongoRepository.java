package cl.tbd.proyecto1.mongo.repositories;

import java.util.List;

import cl.tbd.proyecto1.mongo.models.VoluntarioMongo;

public interface VoluntarioMongoRepository {
    public int countVoluntarios();
    public List<VoluntarioMongo> getVoluntarios();
    public VoluntarioMongo createVoluntario(VoluntarioMongo voluntario);
    public double promedioHabilidades();
}