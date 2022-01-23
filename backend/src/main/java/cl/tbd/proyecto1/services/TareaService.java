package cl.tbd.proyecto1.services;

import cl.tbd.proyecto1.models.Tarea;
import cl.tbd.proyecto1.repositories.TareaRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TareaService {

    private final TareaRepository tareaRepository;
    TareaService(TareaRepository tareaRepository){
        this.tareaRepository = tareaRepository;
    }

    @PostMapping("/tareas/create")
    @ResponseBody
    public Tarea createTarea(@RequestBody Tarea tarea){
        Tarea result = tareaRepository.createTarea(tarea);
        return result;
    }

    @GetMapping("/tareas")
    @ResponseBody
    public List<Tarea> getAllTareas() {
        return tareaRepository.getAllTareas();
    }

    @GetMapping("/tareas/{id}")
    @ResponseBody
    public Tarea getTarea(@PathVariable("id") Integer id){
        return tareaRepository.getTarea(id);
    }
    
    @PutMapping("/tareas/update/{id}")
    @ResponseBody
    public String updateTarea(@PathVariable Integer id, @RequestBody Tarea nuevaTarea) {
        Tarea tarea = getTarea(id);
        if(tarea == null) {
            return String.format("La Tarea con id = %s no existe.", id);
        } else {
            nuevaTarea.setId(tarea.getId());
            if(nuevaTarea.getNombre() == null) {
                nuevaTarea.setNombre(tarea.getNombre());
            }

            if(nuevaTarea.getDescrip() == null) {
                nuevaTarea.setDescrip(tarea.getDescrip());
            }

            if(nuevaTarea.getCantVolRequeridos() == null) {
                nuevaTarea.setCantVolRequeridos(tarea.getCantVolRequeridos());
            }

            if(nuevaTarea.getCantVolInscritos() == null) {
                nuevaTarea.setCantVolInscritos(tarea.getCantVolInscritos());
            }

            if(nuevaTarea.getIdEmergencia() == null) {
                nuevaTarea.setIdEmergencia(tarea.getIdEmergencia());
            }

            if(nuevaTarea.getFInicio() == null) {
                nuevaTarea.setFInicio(tarea.getFInicio());
            }

            if(nuevaTarea.getFFin() == null) {
                nuevaTarea.setFFin(tarea.getFFin());
            }

            if(nuevaTarea.getIdEstado() == null) {
                nuevaTarea.setIdEstado(tarea.getIdEstado());
            }
        }

        Integer result = tareaRepository.updateTarea(nuevaTarea);
        if(result == 0) {
            return String.format("La Tarea con id = %s no se pudo actualizar.", id);
        } else {
            return String.format("La Tarea con id = %s se actualizo.", id);
        }
    }

    @DeleteMapping("/tareas/delete/{id}")
    @ResponseBody
    public String deleteTarea(@PathVariable Integer id){
        if(getTarea(id) == null) {
            return String.format("La Tarea con id = %s no existe.", id);
        } else {
            if(tareaRepository.deleteTarea(id)) {
                return String.format("La Tarea con id = %s no se pudo eliminar.", id);
            } else {
                return String.format("La Tarea con id = %s se elimino!.", id);
            }
        }
    }
    
    //Get tareas por id de emergencia
    @GetMapping("/tareas/emergencia/{id}")
    @ResponseBody
    public List<Tarea> getTareasEmergencia(@PathVariable("id") Integer id){
        return tareaRepository.getTareasEmergencia(id);
    }
    //Cantidad de voluntarios total por emergencia
    @GetMapping("/tareas/voluntarios/{id}")
    @ResponseBody
    public Integer getCantVoluntariosEmergencia(@PathVariable("id") Integer id){
        return tareaRepository.getCantVoluntariosEmergencia(id);
    }

}