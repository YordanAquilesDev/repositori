package Aplicacion.repositoryimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.Modelo.Animal;
import Dominio.repository.AnimalRepository;
import Presentacion.Principal.ConexionPostgresSQL;

public class AnimalRepositoryImpl implements AnimalRepository {
    Connection conexion;

    public AnimalRepositoryImpl() {
        this.conexion = ConexionPostgresSQL.getConexion();

    }

    @Override
    public Animal guardarAnimal(Animal animal) {
        try {
            String sql = """
                    INSERT INTO animal (especie,raza) VALUES
                                            (?,?)
                    RETURNING *;
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setString(1, animal.getEspecie());
            preparar.setString(2, animal.getRaza());
            //El ResulSet no tiene nada
         ResultSet resultado = preparar.executeQuery();
         if(resultado.next()){
             resultado.getInt("id_animal");
             animal.setIdAnimal(resultado.getInt("id_animal"));

         }else{
             System.out.println("LA DB NO RETORNO DATOS PARA EL RESULSET");
         }
            return animal;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }

    }

    @Override
    public Animal traerAnimalPorId(Integer id) {
        try {
            String sql = """
                            SELECT * FROM Animal WHERE id_animal=? ;
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setInt(1, id);
            ResultSet resultado = preparar.executeQuery();
            resultado.next();
            return new Animal(
                    resultado.getInt("id_animal"),
                    resultado.getString("especie"),
                    resultado.getString("raza"));

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public List<Animal> traerAnimalesPorConsumo() {
        List<Animal> animales = new ArrayList<>();
        try {
            String sql = """
                            SELECT a.id_animal,a.especie,a.raza
                             FROM animal a
                             JOIN lote_animal l ON a.id_animal=l.id_animal
                             JOIN consumo_lote c ON l.id_lote=c.id_lote
                             ORDER BY c.cantidad DESC
                             LIMIT 3;
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            ResultSet resultado = preparar.executeQuery();
            while (resultado.next()) {
                animales.add(new Animal(resultado.getInt("id_animal"),
                        resultado.getString("especie"),
                        resultado.getString("raza")));
            }
            return animales;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Animal> traerTodosAnimales() {
        List<Animal> animales = new ArrayList<>();
        try {
            String sql = """
                            SELECT * FROM animal;
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            ResultSet resultado = preparar.executeQuery();
            while (resultado.next()) {
                animales.add(new Animal(resultado.getInt("id_animal"),
                        resultado.getString("especie"),
                        resultado.getString("raza")));
            }
            return animales;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
