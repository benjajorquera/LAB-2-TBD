package cl.tbd.proyecto1.repositories;
import java.util.List;
import cl.tbd.proyecto1.models.Ranking;

public interface RankingRepository {

    public int countRankings();

    public List <Ranking> getAllRankings();
    public Ranking getRanking(Integer id);
    public Ranking createRanking(Ranking ranking);
    public Integer updateRanking(Integer id, Integer id_voluntario, Integer id_tarea, Integer puntaje, Integer flg_invitado, Integer flg_participa);
    public Integer deleteRanking(Integer id);

}