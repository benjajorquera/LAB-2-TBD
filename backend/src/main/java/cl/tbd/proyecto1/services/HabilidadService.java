package cl.tbd.proyecto1.services;

import cl.tbd.proyecto1.models.Habilidad;
import cl.tbd.proyecto1.repositories.HabilidadRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@CrossOrigin
@RestController
public class HabilidadService {

    private final HabilidadRepository habilidadRepository;
    HabilidadService(HabilidadRepository habilidadRepository){
        this.habilidadRepository = habilidadRepository;
    }

    //Get all
    @GetMapping("/habilidades")
    public List<Habilidad> getAllHabilidades(){
        return habilidadRepository.getAllHabilidades();
    }

    //Create
    @PostMapping("/habilidades")
    @ResponseBody
    public Habilidad createHabilidad(@RequestBody Habilidad habilidad){
        Habilidad result = habilidadRepository.createHabilidad(habilidad);
       return result;
    }

    //Get por id
    @GetMapping("/habilidades/{id}")
    public Habilidad getHabilidad(@PathVariable Integer id){
        return habilidadRepository.getHabilidad(id);
    }

    //Update
    @PutMapping("/habilidades")
    public String updateHabilidad(@RequestParam Integer id, @RequestParam(required = false) String descrip){
        Habilidad habilidadAux = getHabilidad(id);
        if(habilidadAux == null){
            return String.format("La habilidad con id = %s no existe.", id);
        }else{
            if(descrip == null){
                descrip = habilidadAux.getDescrip();
            }
        }
        Integer result = habilidadRepository.updateHabilidad(id,descrip);
        if(result == 0){
            return String.format("La habilidad con id = %s no se pudo actualizar.", id);
        }
        else{
            return String.format("La habilidad con id = %s se actualizo.", id);
        }
    }

    //Delete
    @DeleteMapping("/habilidades/delete/{id}")
    public String deleteHabilidad(@PathVariable Integer id){     
        Habilidad aux = getHabilidad(id);
        if(aux == null){
            return String.format("La habilidad con id = %s no existe.", id);
        }

        Integer result = habilidadRepository.deleteHabilidad(id);
        if(result == 1){
            return String.format("La habilidad con id = %s se elimino, junto a sus dependencias.", id);
        }else{
            return String.format("La habilidad con id = %s no se pudo eliminar", id);
        }
    }
}
