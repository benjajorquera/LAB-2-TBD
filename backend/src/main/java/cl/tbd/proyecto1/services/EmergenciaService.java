package cl.tbd.proyecto1.services;

import java.sql.Date;
import java.util.List;

import cl.tbd.proyecto1.repositories.EmeHabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.tbd.proyecto1.models.EmeHabilidad;
import cl.tbd.proyecto1.models.Emergencia;
import cl.tbd.proyecto1.models.EmergenciaReq;
import cl.tbd.proyecto1.repositories.EmergenciaRepository;
import org.sql2o.Sql2o;


@CrossOrigin
@RestController
public class EmergenciaService {

    @Autowired
    private Sql2o sql2o;

    private final EmergenciaRepository emergenciaRepository;
    private final EmeHabilidadRepository emeHabilidadRepository;
    EmergenciaService(EmergenciaRepository emergenciaRepository, EmeHabilidadRepository emeHabilidadRepository) {
        this.emergenciaRepository = emergenciaRepository;
        this.emeHabilidadRepository = emeHabilidadRepository;
    }

    //GetAll
    @GetMapping("/emergencias")
    public List<Emergencia> getAllEmergencia(){
        return emergenciaRepository.getAllEmergencia();
    }

    //GetOne    
    @GetMapping("/emergencias/{id}")
    public Emergencia getEmergencia(@PathVariable Integer id){
        return emergenciaRepository.getEmergencia(id);
    }


    //Create
    @PostMapping("/emergencias/create")
    @ResponseBody
    public Emergencia createEmergencia(@RequestBody Emergencia emergencia){
       Emergencia result = emergenciaRepository.createEmergencia(emergencia);
       return result;
    }


    //Update
    @PutMapping("/emergencias/update")
    public String updateEmergencia(@RequestParam Integer id, @RequestParam(required = false) String nombre, @RequestParam(required = false) String descrip,
     @RequestParam(required = false) Date finicio, @RequestParam(required = false) Date ffin, @RequestParam(required = false) Integer id_institucion){
        
        Emergencia emergenciaAux = getEmergencia(id);

        if(emergenciaAux == null){
            return String.format("La emergencia con id = %s no existe.", id);
        }else{
            if(nombre == null){
                nombre = emergenciaAux.getName(); 
            }
            if(descrip == null){
                descrip = emergenciaAux.getDescrip();
            }
            if(finicio == null){
                finicio = emergenciaAux.getFinicio();
            }
            if(ffin == null){
                ffin = emergenciaAux.getFfin();
            }
            if(id_institucion == null){
                id_institucion = emergenciaAux.getId_institucion();
            }
        }
        Integer result = emergenciaRepository.updateEmergencia(id, nombre, descrip, finicio, ffin, id_institucion);
        if(result == 0){
            return String.format("La emergencia con id = %s no se pudo actualizar.", id);
        }else{
            return String.format("La emergencia con id = %s se actualizo.", id);
        }
    }

    @PostMapping("/emergencias/createVue")
    @ResponseBody
    public Emergencia createEmergenciaVue(@RequestBody EmergenciaReq emr){
        Emergencia em = new Emergencia();
        em.setName(emr.getName());
        em.setDescrip(emr.getDescrip());
        em.setFinicio(emr.getFinicio());
        em.setFfin(emr.getFfin());
        em.setId_institucion(emr.getId_institucion());
        Emergencia result = emergenciaRepository.createEmergencia(em);
        if (result != null){
            for(int i=0; i< emr.getHabilidades().size(); i++){
                //Llamar a la funcion para crear eme_habilidad con result.getId() y idsHabilidades(i)
                EmeHabilidad aux = new EmeHabilidad(result.getId(),emr.getHabilidades().get(i));
                //EmeHabilidadService e = new EmeHabilidadService(EmeHabilidadRepository emeHabilidadRepository);
                emeHabilidadRepository.createEmeHabilidad(aux);
            }
        }
        return result;
    }

    //DeleteOne
    @DeleteMapping("/emergencias/delete/{id}")
    public String deleteEmergencia(@PathVariable Integer id){
        Emergencia emergenciaAux = getEmergencia(id);
        if(emergenciaAux == null){
            return String.format("La emergencia con id = %s no existe.", id);
        }

        Integer result = emergenciaRepository.deleteEmergencia(id);
        if(result == 1){
            return String.format("La emergencia con id = %s se elimino, junto a sus dependencias.", id);
        }else{
            return String.format("La emergencia con id = %s no se pudo eliminar", id);
        }
    }
}

