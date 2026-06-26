package Aplicacion.repositoryimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.Modelo.Animal;
import Presentacion.Principal.ConexionPostgresSQL;

public class AnimalRepositoryImpl implements AnimalRepository {
    // guardar
    @Override
    public int save(Animal animal) {
        Connection conexion = null;
        PreparedStatement preparar = null;
        int resultado = -1;
        try {
            String sql = """
                    INSERT INTO animal (especie,raza) VALUES
                                            (?,?)
                    RETURNING *;
                    """;
            conexion = ConexionPostgresSQL.getConexion();
            preparar = conexion.prepareStatement(sql);
            preparar.setString(1, animal.getEspecie());
            preparar.setString(2, animal.getRaza());
            resultado = preparar.executeUpdate();
            return resultado;
        } catch (SQLException e) {
            return resultado;
        } finally {
            try {
                if (conexion != null)
                    conexion.close();
                if (preparar != null)
                    preparar.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // traer por id
    @Override
    public Animal finById(Integer id) {
        Connection conexion = null;
        PreparedStatement preparar = null;
        ResultSet resultado = null;
        try {
            String sql = """
                            SELECT * FROM Animal WHERE id_animal=? ;
                    """;
            conexion = ConexionPostgresSQL.getConexion();
            preparar = conexion.prepareStatement(sql);
            preparar.setInt(1, id);
            resultado = preparar.executeQuery();
            resultado.next();
            return new Animal(
                    resultado.getInt("id_animal"),
                    resultado.getString("especie"),
                    resultado.getString("raza"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conexion != null)
                    conexion.close();
                if (preparar != null)
                    preparar.close();
                if (resultado != null)
                    resultado.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // listar animales por consumo
    @Override
    public List<Animal> finAllConsumer() {
        List<Animal> animales = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement preparar = null;
        ResultSet resultado = null;
        try {
            String sql = """
                            SELECT a.id_animal,a.especie,a.raza
                             FROM animal a
                             JOIN lote_animal l ON a.id_animal=l.id_animal
                             JOIN consumo_lote c ON l.id_lote=c.id_lote
                             ORDER BY c.cantidad DESC
                             LIMIT 3;
                    """;
            conexion = ConexionPostgresSQL.getConexion();
            preparar = conexion.prepareStatement(sql);
            resultado = preparar.executeQuery();
            while (resultado.next()) {
                animales.add(new Animal(resultado.getInt("id_animal"),
                        resultado.getString("especie"),
                        resultado.getString("raza")));
            }
            return animales;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conexion != null)
                    conexion.close();
                if (preparar != null)
                    preparar.close();
                if (resultado != null)
                    resultado.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // listar todos
    @Override
    public List<Animal> finAll() {
        List<Animal> animales = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement preparar = null;
        ResultSet resultado = null;
        try {
            String sql = """
                            SELECT * FROM animal;
                    """;
            conexion = ConexionPostgresSQL.getConexion();
            preparar = conexion.prepareStatement(sql);
            resultado = preparar.executeQuery();
            while (resultado.next()) {
                animales.add(new Animal(resultado.getInt("id_animal"),
                        resultado.getString("especie"),
                        resultado.getString("raza")));
            }
            return animales;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conexion != null)
                    conexion.close();
                if (preparar != null)
                    preparar.close();
                if (resultado != null)
                    resultado.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public int update(Animal animal) {
        PreparedStatement preparar = null;
        Connection conexion = null;
        int resultado = -1;
        try{
            String sql = """
                            UPDATE animal
                            SET especie = ?, raza = ?
                            WHERE id_animal = ?;
            """;
            conexion = ConexionPostgresSQL.getConexion();
            preparar=conexion.prepareStatement(sql);
            preparar.setString(1, animal.getEspecie());
            preparar.setString(2, animal.getRaza());
            preparar.setInt(3,animal.getIdAnimal());
            resultado = preparar.executeUpdate();
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if(conexion!=null) conexion.close();
                if(preparar!=null) preparar.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    public int delete(Integer id) {

        Connection conexion = null;
        PreparedStatement preparar = null;

        int resultado = -1;
        try{
            String sql = """
                            DELETE FROM animal WHERE id_animal = ?;
            """;
            conexion = ConexionPostgresSQL.getConexion();
            preparar=conexion.prepareStatement(sql);
            preparar.setInt(1, id);
            resultado = preparar.executeUpdate();
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
