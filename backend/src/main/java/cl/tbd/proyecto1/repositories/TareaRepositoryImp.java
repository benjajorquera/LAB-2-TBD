package cl.tbd.proyecto1.repositories;

import cl.tbd.proyecto1.models.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class TareaRepositoryImp implements TareaRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public Tarea getTarea(Integer id){
        String getSql =
            "SELECT * " +
            "FROM tarea " +
            "WHERE id = :id";

        try(Connection con = sql2o.open()) {
            return con.createQuery(getSql)
                .addParameter("id", id)
                .executeAndFetchFirst(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Tarea> getAllTareas() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM tarea")
                    .executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteTarea(Integer id){
        String deleteSql = "DELETE FROM tarea WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(deleteSql)
                .addParameter("id", id)
                .executeAndFetch(Tarea.class);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Tarea createTarea(Tarea tarea) {
        try(Connection conn = sql2o.open()){
            int insertedId=(int) conn.createQuery("INSERT INTO tarea (nombre, descrip, " +
                "cant_vol_requeridos, cant_vol_inscritos, id_emergencia, " +
                "finicio, ffin, id_estado) values (:tareaNombre, " +
                ":tareaDescrip, :tareaCantVolRequeridos, :tareaCantVolInscritos, "+
                ":tareaIdEmergencia, :tareaFInicio, :tareaFFin, :tareaIdEstado)")
                .addParameter("tareaNombre", tarea.getNombre())
                .addParameter("tareaDescrip", tarea.getDescrip())
                .addParameter("tareaCantVolRequeridos", tarea.getCantVolRequeridos())
                .addParameter("tareaCantVolInscritos", tarea.getCantVolInscritos())
                .addParameter("tareaIdEmergencia", tarea.getIdEmergencia())
                .addParameter("tareaFInicio", tarea.getFInicio())
                .addParameter("tareaFFin", tarea.getFFin())
                .addParameter("tareaIdEstado", tarea.getIdEstado())
                .executeUpdate().getKey();
            tarea.setId(insertedId);
            return tarea;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    @Override
    public Integer updateTarea(Tarea nuevaTarea) {
        String updateSql = "UPDATE tarea SET nombre = :tareaNombre, descrip = :tareaDescrip, " +
            "cant_vol_requeridos = :tareaCantVolRequeridos, cant_vol_inscritos = :tareaCantVolInscritos, " + 
            "id_emergencia = :tareaIdEmergencia, finicio = :tareaFInicio, ffin = :tareaFFin, id_estado = :tareaIdEstado WHERE id = :tareaId";

        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                .addParameter("tareaId", nuevaTarea.getId())
                .addParameter("tareaNombre", nuevaTarea.getNombre())
                .addParameter("tareaDescrip", nuevaTarea.getDescrip())
                .addParameter("tareaCantVolRequeridos", nuevaTarea.getCantVolRequeridos())
                .addParameter("tareaCantVolInscritos", nuevaTarea.getCantVolInscritos())
                .addParameter("tareaIdEmergencia", nuevaTarea.getIdEmergencia())
                .addParameter("tareaFInicio", nuevaTarea.getFInicio())
                .addParameter("tareaFFin", nuevaTarea.getFFin())
                .addParameter("tareaIdEstado", nuevaTarea.getIdEstado())
                .executeUpdate();
            return 1;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    @Override
    public List<Tarea> getTareasEmergencia(Integer id) {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM tarea WHERE id_emergencia = :id")
                    .addParameter("id", id)
                    .executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Integer getCantVoluntariosEmergencia(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT SUM(cant_vol_inscritos) FROM tarea WHERE id_emergencia = :id")
                    .addParameter("id", id)
                    .executeScalar(Integer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }       
    }

    @Override
    // getTotalTareasEmergencia
    public Integer getTotalTareasEmergencia(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT COUNT(*) FROM tarea WHERE id_emergencia = :id")
                    .addParameter("id", id)
                    .executeScalar(Integer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }       
    }

    @Override
    // getTotalTareasFinalizadasEmergencia
    public Integer getTotalTareasFinalizadasEmergencia(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT COUNT(*) FROM tarea WHERE id_emergencia = :id AND id_estado = 2")
                    .addParameter("id", id)
                    .executeScalar(Integer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }       
    }
    
}
