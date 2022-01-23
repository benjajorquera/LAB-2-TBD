package cl.tbd.proyecto1.repositories;

import cl.tbd.proyecto1.models.Ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;


@Repository
public class RankingRepositoryImp implements RankingRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countRankings() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM ranking").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public  List<Ranking> getAllRankings(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from ranking")
                    .executeAndFetch(Ranking.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Ranking getRanking(Integer id){

        String sql = "SELECT * FROM ranking WHERE id = :id";

        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(Ranking.class);
        }
    }
    
    @Override
    public Ranking createRanking(Ranking ranking) {

        try (Connection conn = sql2o.open()){ 
            int insertedId = (int) conn.createQuery("INSERT INTO ranking (id_voluntario, id_tarea, puntaje, flg_invitado, flg_participa) VALUES (:id_voluntario2, :id_tarea2, :puntaje2, :flg_invitado2, :flg_participa2)")  

                .addParameter("id_voluntario2", ranking.getId_voluntario())
                .addParameter("id_tarea2", ranking.getId_tarea())
                .addParameter("puntaje2", ranking.getPuntaje())
                .addParameter("flg_invitado2", ranking.getFlg_invitado())
                .addParameter("flg_participa2", ranking.getFlg_participa())
                .executeUpdate().getKey();
                ranking.setId(insertedId);
            return ranking;      
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer updateRanking(Integer id, Integer id_voluntario, Integer id_tarea, Integer puntaje, Integer flg_invitado, Integer flg_participa) {

        String updateSql = "UPDATE ranking SET id_voluntario = :id_voluntario2, id_tarea = :id_tarea2, puntaje = :puntaje2, flg_invitado = :flg_invitado2, flg_participa = :flg_participa2 WHERE id = :id";
        
        try (Connection con = sql2o.open()) { 
            con.createQuery(updateSql)
            .addParameter("id", id)
            .addParameter("id_voluntario2", id_voluntario)
            .addParameter("id_tarea2", id_tarea)
            .addParameter("puntaje2", puntaje)
            .addParameter("flg_invitado2", flg_invitado)
            .addParameter("flg_participa2", flg_participa)
            .executeUpdate();
            return 1;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }        
    }

    @Override
    public Integer deleteRanking(Integer id){
        String sql ="DELETE FROM ranking WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                .addParameter("id", id)
                .executeUpdate();          
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
        return 1;
    }
}