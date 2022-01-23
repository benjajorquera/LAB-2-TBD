package cl.tbd.proyecto1.services;

import cl.tbd.proyecto1.models.Institucion;
import cl.tbd.proyecto1.repositories.InstitucionRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin
@RestController
public class InstitucionService {

    private final InstitucionRepository institucionRepository;
    InstitucionService(InstitucionRepository institucionRepository){
        this.institucionRepository = institucionRepository;
    }

    //GetAll
    @GetMapping("/instituciones")
    public List<Institucion> getAllInstitucion(){
        return institucionRepository.getAllInstitucion();
    }
    
    //GetOne
    @GetMapping("/instituciones/{id}")
    public Institucion getInstitucion(@PathVariable Integer id){
        return institucionRepository.getInstitucion(id);
    }

    //Create
    @PostMapping("/instituciones/create")
    @ResponseBody
    public Institucion createInstitucion(@RequestBody Institucion institucion){
       Institucion result = institucionRepository.createInstitucion(institucion);
       return result;
    }

    //Update
    @PutMapping("/instituciones/update")
    public String updateInstitucion(@RequestParam Integer id, @RequestParam(required = false) String nombre, @RequestParam(required = false) String descrip){
        
        Institucion institucionAux = getInstitucion(id);
        if(institucionAux == null){
            return String.format("La institucion con id = %s no existe.", id);
        }else{
            if(nombre == null){
                nombre = institucionAux.getName(); 
            }
            if(descrip == null){
                descrip = institucionAux.getDescrip();
            }
        }
        Integer result = institucionRepository.updateInstitucion(id, nombre, descrip);
        if(result == 0){
            return String.format("La institucion con id = %s no se pudo actualizar.", id);
        }else{
            return String.format("La institucion con id = %s se actualizo.", id);
        }
    }

    //Delete
    @DeleteMapping("/instituciones/delete/{id}")
    public String deleteInstitucion(@PathVariable Integer id){   
        Institucion aux = getInstitucion(id);
        if(aux == null){
            return String.format("La institucion con id = %s no existe.", id);
        }

        Integer result = institucionRepository.deleteInstitucion(id);
        if(result == 1){
            return String.format("La institucion con id = %s se elimino, junto a sus dependencias.", id);
        }else{
            return String.format("La institucion con id = %s no se pudo eliminar", id);
        }
    }
}



