package cl.tbd.proyecto1.repositories;

import cl.tbd.proyecto1.models.VolHabilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class VolHabilidadRepositoryImp implements VolHabilidadRepository{

    @Autowired
    private Sql2o sql2o;

    // Get all
    @Override
    public List<VolHabilidad> getAllVolHabilidad() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM vol_habilidad")
                    .executeAndFetch(VolHabilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Get por id
    @Override
    public VolHabilidad getVolHabilidad(Integer id) {
        String sql = "SELECT * " +
                "FROM vol_habilidad " +
                "WHERE id = :id";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(VolHabilidad.class);
        }
    }

    // Create
    @Override
    public VolHabilidad createVolHabilidad(VolHabilidad volHabilidad){
        final String insertQuery = "INSERT INTO vol_habilidad (id_voluntario,id_habilidad) " +
                "VALUES (:id_volVH,:id_habVH)";

        try (Connection conn = sql2o.open()) {
                int insertedId = (int) conn.createQuery(insertQuery, true)
                    .addParameter("id_volVH", volHabilidad.getIdVoluntario()) 
                    .addParameter("id_habVH", volHabilidad.getIdHabilidad())
                    .executeUpdate().getKey();
                volHabilidad.setId(insertedId);
            return volHabilidad;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Update
    @Override
    public Integer updateVolHabilidad(Integer id, Integer id_vol, Integer id_hab){
        String upadateSql = "UPDATE vol_habilidad SET id_voluntario = :id_vol, "+
        "id_habilidad = :id_hab " +"WHERE id = :id";

        try (Connection con = sql2o.open()){
            con.createQuery(upadateSql)
            .addParameter("id", id)
            .addParameter("id_vol", id_vol)
            .addParameter("id_hab", id_hab)
            .executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
   
    //Delete
    @Override
    public Integer deleteVolHabilidad(Integer id){
        String sql = "DELETE FROM vol_habilidad WHERE id = :id";
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
