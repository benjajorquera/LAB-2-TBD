package cl.tbd.proyecto1.mongo.services;

import java.util.List;
import java.util.Date;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.tbd.proyecto1.models.Voluntario;
import cl.tbd.proyecto1.mongo.models.VoluntarioMongo;
import cl.tbd.proyecto1.mongo.repositories.VoluntarioMongoRepository;

@RestController
public class VoluntarioMongoService {
    
    private final VoluntarioMongoRepository voluntarioRepository;
    VoluntarioMongoService(VoluntarioMongoRepository voluntarioRepository){
        this.voluntarioRepository = voluntarioRepository;
    }

    //Get from ID
    // @GetMapping("/voluntario/{id}")
    // public Voluntario getVoluntario(@PathVariable Integer id) {
    //     return voluntarioRepository.getVoluntario(id);
    // }

    //contador
    @GetMapping("mongo/voluntarios/count")
    public String countVoluntarios(){
        int total = voluntarioRepository.countVoluntarios();
        return String.format("Tienes %s voluntarios!!", total);
    }

    //get all
    @GetMapping("/mongo/voluntarios")
    public List<VoluntarioMongo> getVoluntarios(){
        return voluntarioRepository.getVoluntarios();
    }

    //create
    @PostMapping("/mongo/voluntarios/create")
    @ResponseBody
    public VoluntarioMongo createVoluntario(@RequestBody VoluntarioMongo voluntario){
        VoluntarioMongo result = voluntarioRepository.createVoluntario(voluntario);
        return result;
    }

    //promedio
    @GetMapping("mongo/voluntarios/promedioHabilidades")
    public String promedioHabilidades(){
        double total = voluntarioRepository.promedioHabilidades();
        return String.format("Los voluntarios tienen en promedio %s habilidades.", total);
    }

     //Update
     //@PutMapping("mongo/voluntarios/update")
     /* public String updateVoluntario(@RequestParam Integer id, @RequestParam(required = false) String nombre, @RequestParam(required = false) Date fNacimiento){
         Voluntario voluntarioAux = getVoluntario(id);
         if(voluntarioAux == null){
             return String.format("EL voluntario con id = %s no existe.", id);
         }else{
             if(nombre == null){
                 nombre = voluntarioAux.getName();
             }
             if(fNacimiento == null){
                 fNacimiento = voluntarioAux.getFnacimiento();
             }
         }
         Integer result = voluntarioRepository.updateVoluntario(id,nombre,fNacimiento);
         if(result == 0){
             return String.format("El voluntario con id = %s no se pudo actualizar.", id);
         }
         else{
             return String.format("El Voluntario con id = %s se actualizo.", id);
         }
     } */
 
     //Delete
     @DeleteMapping("mongo/voluntarios/delete/{id}")
     
     public String deleteVoluntario(@PathVariable Integer id){
         //Voluntario aux = getVoluntario(id);
        //  if(aux == null){
        //      return String.format("El voluntario con id = %s no existe.", id);
        //  }
 
         Integer result = voluntarioRepository.deleteVoluntario(id);
         if(result == 1){
             return String.format("El voluntario con id = %s se elimino, junto a sus dependencias.", id);
         }else{
             return String.format("El voluntario con id = %s no se pudo eliminar", id);
         }
     }
}