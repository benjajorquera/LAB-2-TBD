package cl.tbd.proyecto1.services;

import cl.tbd.proyecto1.models.Ranking;
import cl.tbd.proyecto1.repositories.RankingRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@CrossOrigin
@RestController
public class RankingService {

    private final RankingRepository rankingRepository; 
    RankingService(RankingRepository rankingRepository){
        this.rankingRepository = rankingRepository;
    }

    @GetMapping("/rankings")
    public List<Ranking> getAllRankings() {
        return rankingRepository.getAllRankings();
    }

    @GetMapping("/ranking/{id}")
    public Ranking getRanking(@PathVariable Integer id) {
        return rankingRepository.getRanking(id);
    }

    @GetMapping("/rankings/count")
    public String countRankings(){
        int total = rankingRepository.countRankings();
        return String.format("hay %s rankings registrados", total);
    }

    @PostMapping("/rankings")
    @ResponseBody
    public Ranking createRanking(@RequestBody Ranking ranking){
        Ranking result = rankingRepository.createRanking(ranking);
        return result;
    }

    @PutMapping("/rankings/update/{id}/{id_voluntario}/{id_tarea}/{puntaje}/{flg_invitado}/{flg_participa}")
    @ResponseBody
    public String updateRanking(@PathVariable Integer id, @PathVariable Integer id_voluntario, @PathVariable Integer id_tarea, @PathVariable Integer puntaje, @PathVariable Integer flg_invitado, @PathVariable Integer flg_participa){
        Integer result = rankingRepository.updateRanking(id, id_voluntario, id_tarea, puntaje, flg_invitado, flg_participa);
        if(result == 0){
            return String.format("El Ranking con id: %s No se pudo", id);
        }else{
            return String.format("El Ranking con id: %s Sí se pudo", id);
        }
    }

    //Delete
    @DeleteMapping("/rankings/delete/{id}")
    public String deleteRanking(@PathVariable Integer id){
        Ranking aux = getRanking(id);
        if(aux == null){
            return String.format("El ranking con id = %s no existe.", id);
        }

        Integer result = rankingRepository.deleteRanking(id);
        if(result == 1){
            return String.format("El ranking con id = %s se elimino, junto a sus dependencias.", id);
        }else{
            return String.format("El ranking con id = %s no se pudo eliminar", id);
        }
    }

}
