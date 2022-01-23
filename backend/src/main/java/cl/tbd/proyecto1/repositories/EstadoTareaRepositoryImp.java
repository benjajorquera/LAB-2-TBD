package cl.tbd.proyecto1.repositories;

import cl.tbd.proyecto1.models.EstadoTarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class EstadoTareaRepositoryImp implements EstadoTareaRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public EstadoTarea getEstadoTarea(Integer id){
        String sql =
            "SELECT * " +
            "FROM estado_tarea " +
            "WHERE id = :id";

        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(EstadoTarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    

    @Override
    public List<EstadoTarea> getAllEstadosTareas() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM estado_tarea")
                    .executeAndFetch(EstadoTarea.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public EstadoTarea createEstadoTarea(EstadoTarea estadoTarea) {
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO estado_tarea (descrip) values (:estadoTareaDescrip)") 
                .addParameter("estadoTareaDescrip", estadoTarea.getDescrip())
                .executeUpdate().getKey();
            estadoTarea.setId(insertedId);
            return estadoTarea;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    @Override
    public Integer deleteEstadoTarea(Integer id){
        String sql = "DELETE FROM estado_tarea WHERE id = :id";
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
    @Override
    public Integer updateEstadoTarea(EstadoTarea nuevoEstadoTarea) {
        String updateSql = "UPDATE estado_tarea SET descrip = :estadoTareaDescrip WHERE id = :estadoTareaId";

        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                .addParameter("estadoTareaId", nuevoEstadoTarea.getId())
                .addParameter("estadoTareaDescrip", nuevoEstadoTarea.getDescrip())
                .executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
