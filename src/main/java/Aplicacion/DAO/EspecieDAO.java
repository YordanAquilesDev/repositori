package Aplicacion.DAO;

import Aplicacion.utils.ConexionMySQL;
import Dominio.Modelo.Especie;
import Dominio.repository.ICRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EspecieDAO implements ICRUD<Especie, Integer> {

    @Override
    public int save(Especie beans) {
        String sql = "INSERT INTO Especie(nombre) VALUES (?)";

        try (Connection conexion = ConexionMySQL.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, beans.getNombre());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Especie beans) {
        String sql = "UPDATE Especie SET nombre = ? WHERE idEspecie = ?";

        try (Connection conexion = ConexionMySQL.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, beans.getNombre());
            ps.setInt(2, beans.getIdEspecie());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Especie WHERE idEspecie = ?";

        try (Connection conexion = ConexionMySQL.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Especie> findById(Integer id) {
        String sql = "SELECT * FROM Especie WHERE idEspecie = ?";

        try (Connection conexion = ConexionMySQL.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapear(rs));
                }
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Especie> findAll() {
        String sql = "SELECT * FROM Especie";
        List<Especie> lista = new ArrayList<>();

        try (Connection conexion = ConexionMySQL.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveAndFindId(Especie beans) {
        String sql = "INSERT INTO Especie(nombre) VALUES (?)";

        try (Connection conexion = ConexionMySQL.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, beans.getNombre());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Especie mapear(ResultSet rs) throws SQLException {
        return new Especie(
                rs.getInt("idEspecie"),
                rs.getString("nombre")
        );
    }
}
