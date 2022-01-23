package cl.tbd.proyecto1.services;

import cl.tbd.proyecto1.models.VolHabilidad;
import cl.tbd.proyecto1.repositories.VolHabilidadRepository;

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
public class VolHabilidadService {
    
    private final VolHabilidadRepository volHabilidadRepository;
    VolHabilidadService(VolHabilidadRepository volHabilidadRepository){
        this.volHabilidadRepository = volHabilidadRepository;
    }

    @GetMapping("/vol_habilidades")
    public List<VolHabilidad> getAllVolHabilidad() {
        return volHabilidadRepository.getAllVolHabilidad();
    }

    //Get from ID
    @GetMapping("/vol_habilidad/{id}")
    public VolHabilidad getVolHabilidad(@PathVariable Integer id) {
        return volHabilidadRepository.getVolHabilidad(id);
    }

    //Create
    @PostMapping("/vol_habilidad/create")
    @ResponseBody
    public VolHabilidad createVolHabilidad(@RequestBody VolHabilidad volHabilidad){
       VolHabilidad result = volHabilidadRepository.createVolHabilidad(volHabilidad);
       return result;
    }

    //Update
    @PutMapping("/vol_habilidades")
    public String updateVolHabilidad(@RequestParam Integer id, @RequestParam(required = false) Integer id_voluntario, @RequestParam(required = false) Integer id_habilidad){
        VolHabilidad volHabilidadAux = getVolHabilidad(id);
        if(volHabilidadAux == null){
            return String.format("EL vol_habilidad con id = %s no existe.", id);
        }else{
            if(id_voluntario == null){
                id_voluntario = volHabilidadAux.getIdVoluntario();
            }
            if(id_habilidad == null){
                id_habilidad = volHabilidadAux.getIdHabilidad();
            }
        }
        Integer result = volHabilidadRepository.updateVolHabilidad(id,id_voluntario,id_habilidad);
        if(result == 0){
            return String.format("El vol_habilidad con id = %s no se pudo actualizar.", id);
        }
        else{
            return String.format("El vol_habilidad con id = %s se actualizo.", id);
        }
    }

    //Delete
    @DeleteMapping("/vol_habilidad/delete/{id}")
    public String deleteVolHabilidad(@PathVariable Integer id){
        VolHabilidad aux = getVolHabilidad(id);
        if(aux == null){
            return String.format("Volntario-habilidad con id = %s no existe.", id);
        }

        Integer result = volHabilidadRepository.deleteVolHabilidad(id);
        if(result == 1){
            return String.format("Volntario-habilidad con id = %s se elimino, junto a sus dependencias.", id);
        }else{
            return String.format("Volntario-habilidad con id = %s no se pudo eliminar", id);
        }
    }
}
