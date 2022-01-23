package cl.tbd.proyecto1.repositories;

import cl.tbd.proyecto1.models.Emergencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.sql.Date;

import java.util.List;

@Repository
public class EmergenciaRepositoryImp implements EmergenciaRepository {

    @Autowired
    private Sql2o sql2o;

    //GetOne
    @Override
    public Emergencia getEmergencia(Integer id){

        final String sql =
            "SELECT id, nombre, descrip, finicio, ffin, id_institucion, st_x(st_astext(location)) AS longitude, st_y(st_astext(location)) AS latitude " +
            "FROM emergencia " +
            "WHERE id = :id";

        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(Emergencia.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //GetAll
    @Override
    public List<Emergencia> getAllEmergencia() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT id, nombre, descrip, finicio, ffin, id_institucion, st_x(st_astext(location)) AS longitude, st_y(st_astext(location)) AS latitude FROM emergencia")
                    .executeAndFetch(Emergencia.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Create
    @Override
    public Emergencia createEmergencia(Emergencia emergencia) {

        final String insertQuery =
            "INSERT INTO emergencia (nombre,descrip,finicio,ffin,id_institucion, location) " +
            "VALUES (:nombreE,:descripE,:finicioE,:ffinE,:id_institucionE, ST_GeomFromText(:point, 4326))";

        String point = "POINT(" + emergencia.getLongitude() + " " + emergencia.getLatitude() + ")";

        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery(insertQuery, true)
                    .addParameter("nombreE", emergencia.getName())
                    .addParameter("descripE", emergencia.getDescrip())
                    .addParameter("finicioE", emergencia.getFinicio())
                    .addParameter("ffinE", emergencia.getFfin())
                    .addParameter("id_institucionE", emergencia.getId_institucion())
                    .addParameter("point", point)
                    .executeUpdate().getKey();
                emergencia.setId(insertedId);
            return emergencia;        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    //Update
    @Override
    public Integer updateEmergencia(Integer id, String nombreE, String descripE, Date finicioE, Date ffinE, Integer id_institucionE) {

        String updateSql = 
        "UPDATE emergencia " +
        "SET nombre = :nombreE, descrip = :descripE, finicio = :finicioE, ffin = :ffinE, id_institucion = :id_institucionE  " +
        "WHERE id = :id";
        
        try (Connection con = sql2o.open()) { 
            con.createQuery(updateSql)
            .addParameter("id", id)
            .addParameter("nombreE", nombreE)
            .addParameter("descripE", descripE)
            .addParameter("finicioE", finicioE)
            .addParameter("ffinE", ffinE)
            .addParameter("id_institucionE", id_institucionE)
            .executeUpdate();
            return 1;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }        
    }

    @Override
    public Integer deleteEmergencia(Integer id) {

        final String sql =
            "DELETE FROM emergencia " +
            "WHERE id = :id";

        try(Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                .addParameter("id", id)
                .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 1;
    }

}