package cl.tbd.proyecto1.repositories;

import cl.tbd.proyecto1.models.EmeHabilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class EmeHabilidadRepositoryImp implements EmeHabilidadRepository {
    
    @Autowired
    private Sql2o sql2o;

    // Get all
    @Override
    public List<EmeHabilidad> getAllEmeHabilidad() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM eme_habilidad")
                    .executeAndFetch(EmeHabilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Get por id
    @Override
    public EmeHabilidad getEmeHabilidad(Integer id) {
        String sql = "SELECT * " +
                "FROM eme_habilidad " +
                "WHERE id = :id";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EmeHabilidad.class);
        }
    }
    // Create
    @Override
    public EmeHabilidad createEmeHabilidad(EmeHabilidad emeHabilidad) {
        final String insertQuery = "INSERT INTO eme_habilidad (id_emergencia,id_habilidad) " +
                "VALUES (:idEme,:idHab)";

        try (Connection conn = sql2o.open()) {
               int insertedId = (int) conn.createQuery(insertQuery, true)
                    .addParameter("idEme", emeHabilidad.getId_emergencia()) 
                    .addParameter("idHab", emeHabilidad.getId_habilidad())
                    .executeUpdate().getKey();
                emeHabilidad.setId(insertedId);
            return emeHabilidad;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    //Update
    @Override
    public Integer updateEmeHabilidad(Integer id, Integer idEme, Integer idHab){
        String upadateSql = "UPDATE eme_habilidad SET id_emergencia = :idEme, "+
        "id_habilidad = :idHab " +"WHERE id = :id";

        try (Connection con = sql2o.open()){
            con.createQuery(upadateSql)
            .addParameter("id", id)
            .addParameter("idEme", idEme)
            .addParameter("idHab", idHab)
            .executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    //Delete
    @Override
    public Integer deleteEmeHabilidad(Integer id){
        String sql = "DELETE FROM eme_habilidad WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                .addParameter("id", id)
                .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 1;
    }

}
