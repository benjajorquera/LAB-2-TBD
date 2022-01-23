package cl.tbd.proyecto1.services;

import cl.tbd.proyecto1.models.EstadoTarea;
import cl.tbd.proyecto1.repositories.EstadoTareaRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class EstadoTareaService {

    private final EstadoTareaRepository estadoTareaRepository;
    EstadoTareaService(EstadoTareaRepository estadoTareaRepository){
        this.estadoTareaRepository = estadoTareaRepository;
    }

    @PostMapping("/estadosTareas")
    @ResponseBody
    public EstadoTarea createTarea(@RequestBody EstadoTarea estadoTarea){
        EstadoTarea result = estadoTareaRepository.createEstadoTarea(estadoTarea);
        return result;
    }

    @GetMapping("/estadosTareas")
    @ResponseBody
    public List<EstadoTarea> getAllTareas() {
        return estadoTareaRepository.getAllEstadosTareas();
    }

    @GetMapping("/estadosTareas/{id}")
    public EstadoTarea getEstadoTarea(@PathVariable("id") Integer id){
        return estadoTareaRepository.getEstadoTarea(id);
    }

    @PutMapping("/estadosTareas/update/{id}")
    @ResponseBody
    public String updateEstadoTarea(@PathVariable Integer id, @RequestBody EstadoTarea nuevoEstadoTarea) {
        EstadoTarea estadoTarea = getEstadoTarea(id);
        if(estadoTarea == null) {
            return String.format("El Estado Tarea con id = %s no existe.", id);
        } else {
            nuevoEstadoTarea.setId(id);
            if(nuevoEstadoTarea.getDescrip() == null) {
                nuevoEstadoTarea.setDescrip(estadoTarea.getDescrip());
            }

        }
        Integer result = estadoTareaRepository.updateEstadoTarea(nuevoEstadoTarea);
        if(result == 0) {
            return String.format("El Estado Tarea con id = %s no se pudo actualizar.", id);
        } else {
            return String.format("El Estado Tarea con id = %s se actualizo.", id);
        }
    }
    

    //DeleteOne
    @DeleteMapping("/estadosTareas/delete/{id}")
    public String deleteEstadoTarea(@PathVariable Integer id){
        EstadoTarea aux = getEstadoTarea(id);
        if(aux == null){
            return String.format("Estado-tarea con id = %s no existe.", id);
        }

        Integer result = estadoTareaRepository.deleteEstadoTarea(id);
        if(result == 1){
            return String.format("Estado-tarea con id = %s se elimino, junto a sus dependencias.", id);
        }else{
            return String.format("Estado-tarea con id = %s no se pudo eliminar", id);
        }
    }
}


