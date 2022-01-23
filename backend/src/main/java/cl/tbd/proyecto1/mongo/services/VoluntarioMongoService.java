package cl.tbd.proyecto1.mongo.services;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.tbd.proyecto1.mongo.models.VoluntarioMongo;
import cl.tbd.proyecto1.mongo.repositories.VoluntarioMongoRepository;

@RestController
public class VoluntarioMongoService {
    
    private final VoluntarioMongoRepository voluntarioRepository;
    VoluntarioMongoService(VoluntarioMongoRepository voluntarioRepository){
        this.voluntarioRepository = voluntarioRepository;
    }
    @GetMapping("mongo/voluntarios/count")
    public String countVoluntarios(){
        int total = voluntarioRepository.countVoluntarios();
        return String.format("Tienes %s voluntarios!!", total);
    }

    @GetMapping("/mongo/voluntarios")
    public List<VoluntarioMongo> getVoluntarios(){
        return voluntarioRepository.getVoluntarios();
    }

    @PostMapping("/mongo/voluntarios")
    @ResponseBody
    public VoluntarioMongo createDog(@RequestBody VoluntarioMongo voluntario){
        VoluntarioMongo result = voluntarioRepository.createVoluntario(voluntario);
        return result;
    }

    @GetMapping("mongo/voluntarios/promedioHabilidades")
    public String promedioHabilidades(){
        double total = voluntarioRepository.promedioHabilidades();
        return String.format("Los voluntarios tienen en promedio %s habilidades.", total);
    }

}