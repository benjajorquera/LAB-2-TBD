package cl.tbd.proyecto1.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "voluntarios", path = "voluntariosMongo")
public interface VoluntarioRepository extends MongoRepository<Voluntario, String> {
    List<Voluntario> findByNombre(@Param("nombre") String nombre);
    
}