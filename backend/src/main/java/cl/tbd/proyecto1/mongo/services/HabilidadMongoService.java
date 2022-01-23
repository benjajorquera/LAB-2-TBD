package cl.tbd.proyecto1.mongo.services;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.tbd.proyecto1.mongo.models.HabilidadMongo;
import cl.tbd.proyecto1.mongo.repositories.HabilidadMongoRepository;

@RestController
public class HabilidadMongoService {
    
    private final HabilidadMongoRepository habilidadRepository;
    HabilidadMongoService(HabilidadMongoRepository habilidadRepository){
        this.habilidadRepository = habilidadRepository;
    }
    @GetMapping("mongo/habilidades/count")
    public String countHabilidades(){
        int total = habilidadRepository.countHabilidades();
        return String.format("Tienes %s habilidades!!", total);
    }

    @GetMapping("/mongo/habilidades")
    public List<HabilidadMongo> getHabilidades(){
        return habilidadRepository.getHabilidades();
    }

    @PostMapping("/mongo/habilidades")
    @ResponseBody
    public HabilidadMongo createDog(@RequestBody HabilidadMongo habilidad){
        HabilidadMongo result = habilidadRepository.createHabilidad(habilidad);
        return result;
    }

}