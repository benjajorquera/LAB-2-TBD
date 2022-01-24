package cl.tbd.proyecto1.mongo.services;

import java.util.List;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
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

    // @GetMapping("/mongo/voluntarios/{id}")
    // public VoluntarioMongo getVoluntario(@PathVariable Integer id){
    //     return voluntarioRepository.getVoluntario(id);
    // }

    // @PutMapping("/mongo/voluntarios")
    // public VoluntarioMongo updateHabilidad(@RequestParam Integer id, @RequestParam(required = false) String nombre, @RequestParam(required = false) Date fnacimiento){
    //     VoluntarioMongo voluntarioAux = getVoluntario(id);
    //     if(voluntarioAux == null){
    //         return null;
    //     }else{
    //         if(nombre == null){
    //             codigo = voluntarioAux.getNombre();
    //         }
    //         if(descrip == null){
    //             descrip = voluntarioAux.getFNacimiento();
    //         }
    //     }
    //     VoluntarioMongo result = VoluntarioMongoRepository.updateVoluntario(voluntarioAux);
    //     return result;
    // }

    //Delete
    // @DeleteMapping("/mongo/voluntarios/delete/{id}")
    // public String deleteVoluntario(@PathVariable Integer id){     
    //     VoluntarioMongo aux = getVoluntario(id);
    //     if(aux == null){
    //         return String.format("El voluntario con id = %s no existe.", id);
    //     }
    //     VoluntarioMongo result = voluntarioRepository.deleteVoluntario(id);
    //     return String.format("El voluntario con id = %s se elimino, junto a sus dependencias.", id);
    // }
}