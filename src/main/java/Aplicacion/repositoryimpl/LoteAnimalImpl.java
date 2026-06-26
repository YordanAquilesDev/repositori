package Aplicacion.repositoryimpl;

import Dominio.Modelo.LoteAnimal;
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

public class LoteAnimalImpl implements CrudGenerico<LoteAnimal, Integer> {

    private final AnimalRepositoryImpl animalRepository;

    public LoteAnimalImpl() {
        this.animalRepository = new AnimalRepositoryImpl();
    }

    @Override
    public int save(LoteAnimal beans) {
        String sql = "INSERT INTO lote_animal (id_animal, fecha_inicio, cantidad_inicio, cantidad_actual, peso_promedio, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, beans.getAnimal().getIdAnimal());
            pstmt.setDate(2, beans.getFechaInicio());
            pstmt.setInt(3, beans.getCantidadInicio());
            pstmt.setInt(4, beans.getCantidadActual());
            pstmt.setDouble(5, beans.getPesoPromedio());
            pstmt.setString(6, beans.getEstadoLote());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(LoteAnimal beans) {
        String sql = "UPDATE lote_animal "
                + "SET id_animal = ?, fecha_inicio = ?, cantidad_inicio = ?, "
                + "cantidad_actual = ?, peso_promedio = ?, estado = ? "
                + "WHERE id_lote = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, beans.getAnimal().getIdAnimal());
            pstmt.setDate(2, beans.getFechaInicio());
            pstmt.setInt(3, beans.getCantidadInicio());
            pstmt.setInt(4, beans.getCantidadActual());
            pstmt.setDouble(5, beans.getPesoPromedio());
            pstmt.setString(6, beans.getEstadoLote());
            pstmt.setInt(7, beans.getIdLote());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM lote_animal WHERE id_lote = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<LoteAnimal> findById(Integer id) {
        String sql = "SELECT * FROM lote_animal WHERE id_lote = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
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
    public List<LoteAnimal> findAll() {
        List<LoteAnimal> list = new ArrayList<>();
        String sql = "SELECT * FROM lote_animal";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapear(rs));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveAndFinId(LoteAnimal beans) {
        String sql = "INSERT INTO lote_animal (id_animal, fecha_inicio, cantidad_inicio, cantidad_actual, peso_promedio, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, beans.getAnimal().getIdAnimal());
            pstmt.setDate(2, beans.getFechaInicio());
            pstmt.setInt(3, beans.getCantidadInicio());
            pstmt.setInt(4, beans.getCantidadActual());
            pstmt.setDouble(5, beans.getPesoPromedio());
            pstmt.setString(6, beans.getEstadoLote());

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

    public LoteAnimal traerPorId(int id) {
        return findById(id).orElse(null);
    }

    public LoteAnimal guardarLoteAnimal(LoteAnimal loteAnimal) {
        int id = saveAndFinId(loteAnimal);
        if (id != -1) {
            loteAnimal.setIdLote(id);
        }
        return loteAnimal;
    }

    private LoteAnimal mapear(ResultSet rs) throws SQLException {
        return new LoteAnimal(
                rs.getInt("id_lote"),
                animalRepository.findById(rs.getInt("id_animal")).orElse(null),
                rs.getDate("fecha_inicio"),
                rs.getInt("cantidad_inicio"),
                rs.getInt("cantidad_actual"),
                rs.getDouble("peso_promedio"),
                rs.getString("estado")
        );
    }
}
