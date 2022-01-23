package cl.tbd.proyecto1.repositories;

import cl.tbd.proyecto1.models.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class HabilidadRepositoryImp implements HabilidadRepository {

    @Autowired
    private Sql2o sql2o;

    // Get all
    @Override
    public List<Habilidad> getAllHabilidades() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM habilidad")
                    .executeAndFetch(Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Get por id
    @Override
    public Habilidad getHabilidad(Integer id) {
        String sql = "SELECT * " +
                "FROM habilidad " +
                "WHERE id = :id";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Habilidad.class);
        }
    }
    // Create
    @Override
    public Habilidad createHabilidad(Habilidad habilidad) {
        final String insertQuery = "INSERT INTO habilidad (descrip) " +
                "VALUES (:descripH)";

        try (Connection conn = sql2o.open()) {
            int insertedId = (int) conn.createQuery(insertQuery, true)
                    .addParameter("descripH", habilidad.getDescrip())
                    .executeUpdate().getKey();
                habilidad.setId(insertedId);
            return habilidad;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Update
    @Override
    public Integer updateHabilidad(Integer idH, String descripH){
        String upadateSql = "UPDATE habilidad SET descrip = :descripH "+
        "WHERE id = :idH";
        try (Connection con = sql2o.open()){
            con.createQuery(upadateSql)
            .addParameter("idH", idH)
            .addParameter("descripH", descripH)
            .executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    //Delete
    @Override
    public Integer deleteHabilidad(Integer id){
        String sql = "DELETE FROM habilidad WHERE id = :id";
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
