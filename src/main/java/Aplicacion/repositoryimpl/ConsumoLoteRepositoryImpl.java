package Aplicacion.repositoryimpl;

import Dominio.Modelo.Animal;
import Dominio.Modelo.ConsumoLote;
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

public class ConsumoLoteRepositoryImpl implements CrudGenerico<ConsumoLote, Integer> {

    private final LoteAnimalRepositoryImpl loteAnimal;
    private final ProductoRepositoryImpl producto;

    public ConsumoLoteRepositoryImpl() {
        this.loteAnimal = new LoteAnimalRepositoryImpl();
        this.producto = new ProductoRepositoryImpl();
    }

    @Override
    public int save(ConsumoLote beans) {
        String sql = "INSERT INTO consumo_lote (id_lote, id_producto, cantidad, fecha) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, beans.getLote().getIdLote());
            pstmt.setInt(2, beans.getProducto().getIdProducto());
            pstmt.setDouble(3, beans.getCantidad());
            pstmt.setDate(4, beans.getFecha());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(ConsumoLote beans) {
        String sql = "UPDATE consumo_lote "
                + "SET id_lote = ?, id_producto = ?, cantidad = ?, fecha = ? "
                + "WHERE id_consumo = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, beans.getLote().getIdLote());
            pstmt.setInt(2, beans.getProducto().getIdProducto());
            pstmt.setDouble(3, beans.getCantidad());
            pstmt.setDate(4, beans.getFecha());
            pstmt.setInt(5, beans.getIdConsumo());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM consumo_lote WHERE id_consumo = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ConsumoLote> findById(Integer id) {
        String sql = "SELECT * FROM consumo_lote WHERE id_consumo = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
    public List<ConsumoLote> findAll() {
        List<ConsumoLote> list = new ArrayList<>();
        String sql = "SELECT * FROM consumo_lote";

        try (Connection conn = ConexionMySQL.getConexionMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapear(rs));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveAndFindId(ConsumoLote beans) {
        String sql = "INSERT INTO consumo_lote (id_lote, id_producto, cantidad, fecha) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, beans.getLote().getIdLote());
            pstmt.setInt(2, beans.getProducto().getIdProducto());
            pstmt.setDouble(3, beans.getCantidad());
            pstmt.setDate(4, beans.getFecha());

            int filas = pstmt.executeUpdate();
            if (filas == 0) {
                return -1;
            }

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ConsumoLote obtenerConsumoPorId(Long id) {
        return findById(id.intValue()).orElse(null);
    }

    public List<ConsumoLote> obtenerConsumoLotePorAnimal(Animal animal) {
        List<ConsumoLote> consumos = new ArrayList<>();
        String sql = "SELECT cl.* FROM consumo_lote cl "
                + "JOIN lote_animal la ON cl.id_lote = la.id_lote "
                + "WHERE la.id_animal = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, animal.getIdAnimal());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    consumos.add(mapear(rs));
                }
            }

            return consumos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ConsumoLote mapear(ResultSet rs) throws SQLException {
        return new ConsumoLote(
                rs.getInt("id_consumo"),
                loteAnimal.findById(rs.getInt("id_lote")).orElse(null),
                rs.getDouble("cantidad"),
                producto.findById(rs.getInt("id_producto")).orElse(null),
                rs.getDate("fecha")
        );
    }
}
