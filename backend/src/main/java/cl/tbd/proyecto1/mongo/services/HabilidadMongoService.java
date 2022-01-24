package cl.tbd.proyecto1.mongo.services;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
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
    public HabilidadMongo createHabilidad(@RequestBody HabilidadMongo habilidad){
        HabilidadMongo result = habilidadRepository.createHabilidad(habilidad);
        return result;
    }    

    @DeleteMapping("/mongo/habilidades/delete/{id}")
    public String deleteHabilidad(@PathVariable Integer id){     
        Integer result = habilidadRepository.deleteHabilidad(id);
        return String.format("La habilidad con id = %s se elimino, junto a sus dependencias.", id);
    } 

    @GetMapping("/mongo/habilidades/{id}")
    public HabilidadMongo getHabilidad(@PathVariable Integer id){
        return habilidadRepository.getHabilidad(id);
    } 
    

}