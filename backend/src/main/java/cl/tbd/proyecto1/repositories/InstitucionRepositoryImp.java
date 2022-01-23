package cl.tbd.proyecto1.repositories;

import cl.tbd.proyecto1.models.Institucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class InstitucionRepositoryImp implements InstitucionRepository {

    @Autowired
    private Sql2o sql2o;

    // GetOne
    public Institucion getInstitucion(Integer id){

        final String sql =
            "SELECT * " +
            "FROM institucion " +
            "WHERE id = :id";

        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(Institucion.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //GetAll
    @Override
    public List<Institucion> getAllInstitucion() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM institucion")
                    .executeAndFetch(Institucion.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Create
    @Override
    public Institucion createInstitucion(Institucion institucion) {
        
        final String insertQuery =
            "INSERT INTO institucion (nombre,descrip) " +
            "VALUES (:nombreI,:descripI)";

        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery(insertQuery, true)
                    .addParameter("nombreI", institucion.getName())
                    .addParameter("descripI", institucion.getDescrip())
                    .executeUpdate().getKey();
            institucion.setId(insertedId);
            return institucion;        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Update
    @Override
    public Integer updateInstitucion(Integer id, String nombreI, String descripI) {

        String updateSql = 
        "UPDATE institucion " +
        "SET nombre = :nombreI, descrip = :descripI " +
        "WHERE id = :id";
        
        try (Connection con = sql2o.open()) { 
            con.createQuery(updateSql)
            .addParameter("id", id)
            .addParameter("nombreI", nombreI)
            .addParameter("descripI", descripI)
            .executeUpdate();
            return 1;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }        
    }

    //DeleteOne
    @Override
    public Integer deleteInstitucion(Integer id){

        String sql ="DELETE FROM institucion WHERE id = :id";
        
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