package cl.tbd.proyecto1.repositories;

import cl.tbd.proyecto1.models.TareaHabilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class TareaHabilidadRepositoryImp implements TareaHabilidadRepository {

    @Autowired
    private Sql2o sql2o;

    // Get all
    @Override
    public List<TareaHabilidad> getAllTareaHabilidad() {
        final String sql = "SELECT * FROM tarea_habilidad";

        try (Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .executeAndFetch(TareaHabilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TareaHabilidad getTareaHabilidad(Integer id) {
        final String sql = "SELECT * FROM tarea_habilidad WHERE id = :id";

        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(TareaHabilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TareaHabilidad createTareaHabilidad(TareaHabilidad tareaHabilidad) {
        final String insertQuery =
                "INSERT INTO tarea_habilidad (id_emehab, id_tarea) " +
                "VALUES (:id_emehab, :id_tarea)";

        try(Connection conn = sql2o.open()){
            int insertedId= (int) conn.createQuery(insertQuery, true)
                    .addParameter("id_emehab", tareaHabilidad.getIdEmeHab())
                    .addParameter("id_tarea", tareaHabilidad.getIdTarea())
                    .executeUpdate().getKey();
                    tareaHabilidad.setId(insertedId);
            return tareaHabilidad;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer updateTareaHabilidad(Integer id, Integer id_emehab, Integer id_tarea) {
        String updateSql = "UPDATE tarea_habilidad SET id_emehab = :id_emehab, id_tarea = :id_tarea WHERE id = :id";

        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                    .addParameter("id", id)
                    .addParameter("id_emehab", id_emehab)
                    .addParameter("id_tarea", id_tarea)
                    .executeUpdate();
            return 1;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean deleteTareaHabilidad(Integer id) {
        String sql ="DELETE FROM tarea_habilidad WHERE id = :id";

        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(TareaHabilidad.class);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }


}
