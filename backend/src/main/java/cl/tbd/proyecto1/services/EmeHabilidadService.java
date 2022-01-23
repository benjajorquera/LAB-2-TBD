package cl.tbd.proyecto1.services;

import cl.tbd.proyecto1.models.EmeHabilidad;
import cl.tbd.proyecto1.repositories.EmeHabilidadRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin
@RestController
public class EmeHabilidadService {
    
    private final EmeHabilidadRepository emeHabilidadRepository;
    EmeHabilidadService(EmeHabilidadRepository emeHabilidadRepository){
        this.emeHabilidadRepository = emeHabilidadRepository;
    }

    @GetMapping("/eme_habilidades")
    public List<EmeHabilidad> getAllEmeHabilidad(){
        return emeHabilidadRepository.getAllEmeHabilidad();
    }

    @PostMapping("/eme_habilidades")
    @ResponseBody
    public EmeHabilidad createEmeHabilidad(@RequestBody EmeHabilidad emeHabilidad){
        EmeHabilidad result = emeHabilidadRepository.createEmeHabilidad(emeHabilidad);
       return result;
    }

    @GetMapping("/eme_habilidades/{id}")
    public EmeHabilidad getEmeHabilidad(@PathVariable Integer id){
        return emeHabilidadRepository.getEmeHabilidad(id);
    }

    @PutMapping("/eme_habilidades")
    public String updateEmeHabilidad(@RequestParam Integer id, @RequestParam(required = false) Integer id_emergencia, @RequestParam(required = false) Integer id_habilidad){
        EmeHabilidad emeHabAux = getEmeHabilidad(id);
        if(emeHabAux == null){
            return String.format("La eme_habilidad con id = %s no existe.", id);
        }else{
            if(id_emergencia == null){
                id_emergencia = emeHabAux.getId_emergencia();
            }
            if(id_habilidad == null){
                id_habilidad = emeHabAux.getId_habilidad();
            }
        }
        Integer result = emeHabilidadRepository.updateEmeHabilidad(id,id_emergencia,id_habilidad);
        if(result == 0){
            return String.format("La eme_habilidad con id = %s no se pudo actualizar.", id);
        }
        else{
            return String.format("La eme_habilidad con id = %s se actualizo.", id);
        }
    }

    //DeleteOne
    @DeleteMapping("/eme_habilidades/delete/{id}")
    public String deleteEmeHabilidad(@PathVariable Integer id){
        EmeHabilidad aux = getEmeHabilidad(id);
        if(aux == null){
            return String.format("Emergencia-habilidad con id = %s no existe.", id);
        }

        Integer result = emeHabilidadRepository.deleteEmeHabilidad(id);
        if(result == 1){
            return String.format("Emergencia-habilidad con id = %s se elimino, junto a sus dependencias.", id);
        }else{
            return String.format("Emergencia-habilidad con id = %s no se pudo eliminar", id);
        }
    }
}
