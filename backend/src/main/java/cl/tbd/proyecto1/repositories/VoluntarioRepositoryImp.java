package cl.tbd.proyecto1.repositories;

import cl.tbd.proyecto1.models.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.sql.Date;

@Repository
public class VoluntarioRepositoryImp implements VoluntarioRepository{
    
    @Autowired
    private Sql2o sql2o;
   
    // Get all
   @Override
   public List<Voluntario> getAllVoluntario() {
       try (Connection conn = sql2o.open()) {
           return conn.createQuery("SELECT id, nombre, fnacimiento, st_x(st_astext(location)) AS longitude, st_y(st_astext(location)) AS latitude FROM voluntario")
                   .executeAndFetch(Voluntario.class);
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return null;
       }
   }

    // Get por id
    @Override
    public Voluntario getVoluntario(Integer id) {
        String sql = "SELECT id, nombre, fnacimiento, st_x(st_astext(location)) AS longitude, st_y(st_astext(location)) AS latitude " +
                "FROM voluntario " +
                "WHERE id = :id";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Voluntario.class);
        }
    }

    // Create
    @Override
    public Voluntario createVoluntario(Voluntario voluntario){
        final String insertQuery = "INSERT INTO voluntario (nombre,fnacimiento, location) " +
                "VALUES (:nombreV,:fnacimientoV, ST_GeomFromText(:point, 4326))";

        String point = "POINT(" + voluntario.getLongitude() + " " + voluntario.getLatitude() + ")";

        try (Connection conn = sql2o.open()) {
                int insertedId = (int) conn.createQuery(insertQuery, true)
                    .addParameter("nombreV", voluntario.getName()) 
                    .addParameter("fnacimientoV", voluntario.getFnacimiento())
                    .addParameter("point", point)
                    .executeUpdate().getKey();
                voluntario.setId(insertedId);
            return voluntario;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Update
    @Override
    public Integer updateVoluntario(Integer id,@RequestParam String nombre,@RequestParam Date fNacimiento){
        String upadateSql = "UPDATE voluntario SET nombre = :nombre, "+
        "fnacimiento = :fNacimiento " +"WHERE id = :id";

        try (Connection con = sql2o.open()){
            con.createQuery(upadateSql)
            .addParameter("id", id)
            .addParameter("nombre", nombre)
            .addParameter("fnacimiento", fNacimiento)
            .executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
   
    //Delete
    @Override
    public Integer deleteVoluntario(Integer id){
        String sql = "DELETE FROM voluntario WHERE id = :id";
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
