package Aplicacion.DAO;

import Dominio.Modelo.Animal;
import Dominio.repository.CrudGenerico;
import Aplicacion.utils.ConexionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimalRepositoryImpl implements CrudGenerico<Animal, Integer> {

    
    @Override
    public int save(Animal beans) {
        String sql = "INSERT INTO animal (especie, raza) VALUES (?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, beans.getEspecie());
            pstmt.setString(2, beans.getRaza());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Animal beans) {
        String sql = "UPDATE animal SET especie = ?, raza = ? WHERE id_animal = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, beans.getEspecie());
            pstmt.setString(2, beans.getRaza());
            pstmt.setInt(3, beans.getIdAnimal());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM animal WHERE id_animal = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Animal> findById(Integer id) {
        String sql = "SELECT * FROM animal WHERE id_animal = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Animal(
                            rs.getInt("id_animal"),
                            rs.getString("especie"),
                            rs.getString("raza")
                    ));
                }
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Animal> findAll() {
        List<Animal> list = new ArrayList<>();
        String sql = "SELECT * FROM animal";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Animal(
                        rs.getInt("id_animal"),
                        rs.getString("especie"),
                        rs.getString("raza")
                ));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveAndFindId(Animal beans) {
        String sql = "INSERT INTO animal (especie, raza) VALUES (?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, beans.getEspecie());
            pstmt.setString(2, beans.getRaza());

            int filas = pstmt.executeUpdate();
            if (filas == 0) return -1;

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }

            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Animal> findAllConsumer() {
        List<Animal> animales = new ArrayList<>();
        String sql = "SELECT a.id_animal, a.especie, a.raza "
                + "FROM animal a "
                + "JOIN lote_animal l ON a.id_animal = l.id_animal "
                + "JOIN consumo_lote c ON l.id_lote = c.id_lote "
                + "ORDER BY c.cantidad DESC "
                + "LIMIT 3";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                animales.add(new Animal(
                        rs.getInt("id_animal"),
                        rs.getString("especie"),
                        rs.getString("raza")
                ));
            }

            return animales;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
