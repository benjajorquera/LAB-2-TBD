package cl.tbd.proyecto1.services;

import cl.tbd.proyecto1.models.Voluntario;
import cl.tbd.proyecto1.repositories.VoluntarioRepository;

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

import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;
    VoluntarioService(VoluntarioRepository voluntarioRepository){
        this.voluntarioRepository = voluntarioRepository;
    }

    //GetAll
    @GetMapping("/voluntarios")
    public List<Voluntario> getAllVoluntario() {
        return voluntarioRepository.getAllVoluntario();
    }

    //Get from ID
    @GetMapping("/voluntario/{id}")
    public Voluntario getVoluntario(@PathVariable Integer id) {
        return voluntarioRepository.getVoluntario(id);
    }

    //Create
    @PostMapping("/voluntario")
    @ResponseBody
    public Voluntario createVoluntario(@RequestBody Voluntario voluntario){
       Voluntario result = voluntarioRepository.createVoluntario(voluntario);
       return result;
    }

    //Update
    @PutMapping("/voluntarios")
    public String updateVoluntario(@RequestParam Integer id, @RequestParam(required = false) String nombre, @RequestParam(required = false) Date fNacimiento){
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
    }

    //Delete
    @DeleteMapping("/voluntarios/delete/{id}")
    public String deleteVoluntario(@PathVariable Integer id){
        Voluntario aux = getVoluntario(id);
        if(aux == null){
            return String.format("El voluntario con id = %s no existe.", id);
        }

        Integer result = voluntarioRepository.deleteVoluntario(id);
        if(result == 1){
            return String.format("El voluntario con id = %s se elimino, junto a sus dependencias.", id);
        }else{
            return String.format("El voluntario con id = %s no se pudo eliminar", id);
        }
    }
}
