package Aplicacion.repositoryimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dominio.Modelo.LoteAnimal;
import Presentacion.Principal.ConexionPostgresSQL;

public class LoteAnimalImpl implements CrudGenerico<LoteAnimal,Integer>{

    private final AnimalServiceImpl animalService;
    public LoteAnimalImpl() {
        this.animalService = new AnimalRepositoryImpl();
    }

    @Override
    public int save(LoteAnimal loteAnimal) {
        Connection connn=null;
        PreparedStatement preparar= null;
        try {
            String sql = """
                    INSERT INTO lote_animal VALUES (?,?,?,?,?,?)
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setInt(1, loteAnimal.getAnimal().getIdAnimal());
            preparar.setDate(2, loteAnimal.getFechaInicio());
            preparar.setInt(3, loteAnimal.getCantidadInicio());
            preparar.setInt(4, loteAnimal.getCantidadActual());
            preparar.setDouble(5, loteAnimal.getPesoPromedio());
            preparar.setString(6, loteAnimal.getEstadoLote());
            return preparar.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LoteAnimal finById(Integer id) {
                Connection connn=null;
        PreparedStatement preparar= null;
        ResultSet resultado= null;
        try {
            String sql = """
                    SELECT * FROM lote_animal
                    WHERE id_lote=?
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setInt(1, id);
            ResultSet resultado = preparar.executeQuery();
            resultado.next();
            return new LoteAnimal(
                    resultado.getInt("id_lote"),
                    animalService.finById(resultado.getInt("id_animal")),
                    resultado.getDate("fecha_inicio"),
                    resultado.getInt("cantidad_inicio"),
                    resultado.getInt("cantidad_actual"),
                    resultado.getDouble("peso_promedio"),
                    resultado.getString("estado_lote"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
             try{
                if(conn!= null) conn.close();
                if(preparar!= null) preparar.close();
                if(resultado!= null) resultado.close();
             }catch( Exception error){
                 error.printStackTrace();
             }
        }

    }
    
}
