package cl.tbd.proyecto1.services;

import cl.tbd.proyecto1.models.TareaHabilidad;
import cl.tbd.proyecto1.repositories.TareaHabilidadRepository;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController
public class TareaHabilidadService {
    
    private final TareaHabilidadRepository tareaHabilidadRepository;
    TareaHabilidadService(TareaHabilidadRepository tareaHabilidadRepository) {
        this.tareaHabilidadRepository = tareaHabilidadRepository;
    }

    @GetMapping("/tarea_habilidad")
    public List<TareaHabilidad> getAllTareaHabilidad() {
        return tareaHabilidadRepository.getAllTareaHabilidad();
    }

    @GetMapping("/tarea_habilidad/{id}")
    public TareaHabilidad getTareaHabilidad(@PathVariable Integer id) {
        return tareaHabilidadRepository.getTareaHabilidad(id);
    }

    @PostMapping("/tarea_habilidad")
    @ResponseBody
    public TareaHabilidad createTareaHabilidad(@RequestBody TareaHabilidad tareaHabilidad){
        return tareaHabilidadRepository.createTareaHabilidad(tareaHabilidad);
    }

    @PutMapping("/tarea_habilidad/update/{id}")
    @ResponseBody
    public String updateTareaHabilidad(@PathVariable Integer id, @RequestParam(required = false) Integer id_emehab, @RequestParam(required = false) Integer id_tarea) {
        TareaHabilidad th = getTareaHabilidad(id);
        if(th == null) {
            return String.format("La Tarea Habilidad con id = %s no existe.", id);
        } else {
            if(id_emehab == null) {
                id_emehab = th.getIdEmeHab();
            }

            if(id_tarea == null) {
                id_tarea = th.getIdTarea();
            }
        }

        Integer result = tareaHabilidadRepository.updateTareaHabilidad(id, id_emehab, id_tarea);
        if(result == 0) {
            return String.format("La Tarea Habilidad con id = %s no se pudo actualizar.", id);
        } else {
            return String.format("La Tarea Habilidad con id = %s se actualizo.", id);
        }
    }

    @DeleteMapping("/tarea_habilidad/delete/{id}")
    @ResponseBody
    public String deleteTareaHabilidad(@PathVariable Integer id){
        if(getTareaHabilidad(id) == null) {
            return String.format("La Tarea Habilidad con id = %s no existe.", id);
        } else {
            if(tareaHabilidadRepository.deleteTareaHabilidad(id)) {
                return String.format("La Tarea Habilidad con id = %s no se pudo eliminar.", id);
            } else {
                return String.format("La Tarea Habilidad con id = %s se elimino!.", id);
            }
        }
    }

}
