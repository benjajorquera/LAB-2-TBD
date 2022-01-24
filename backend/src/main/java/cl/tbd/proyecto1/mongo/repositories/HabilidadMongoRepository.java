package cl.tbd.proyecto1.mongo.repositories;

import java.util.List;

import cl.tbd.proyecto1.mongo.models.HabilidadMongo;

public interface HabilidadMongoRepository {
    public int countHabilidades();
    public List<HabilidadMongo> getHabilidades();
    public HabilidadMongo createHabilidad(HabilidadMongo habilidad);

/*     public HabilidadMongo getHabilidad(Integer id);
    public HabilidadMongo updateHabilidad(HabilidadMongo habilidad);
    public HabilidadMongo deleteHabilidad(Integer id) */
}