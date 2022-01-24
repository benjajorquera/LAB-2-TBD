package cl.tbd.proyecto1.mongo.services;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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


/*     @GetMapping("/mongo/habilidades/{id}")
    public HabilidadMongo getHabilidad(@PathVariable Integer id){
        return habilidadRepository.getHabilidad(id);
    }

    @PutMapping("/mongo/habilidades")
    public HabilidadMongo updateHabilidad(@RequestParam Integer id, @RequestParam(required = false) String codigo, @RequestParam(required = false) String descrip){
        HabilidadMongo habilidadAux = getHabilidad(id);
        if(habilidadAux == null){
            return null;
        }else{
            if(codigo == null){
                codigo = habilidadAux.getCodigo();
            }
            if(descrip == null){
                descrip = habilidadAux.getDescrip();
            }
        }
        HabilidadMongo result = habilidadRepository.updateHabilidad(habilidadAux);
        return result;
    }

    //Delete
    @DeleteMapping("/mongo/habilidades/delete/{id}")
    public String deleteHabilidad(@PathVariable Integer id){     
        HabilidadMongo aux = getHabilidad(id);
        if(aux == null){
            return String.format("La habilidad con id = %s no existe.", id);
        }
        HabilidadMongo result = habilidadRepository.deleteHabilidad(id);
        return String.format("La habilidad con id = %s se elimino, junto a sus dependencias.", id);
    } */
}