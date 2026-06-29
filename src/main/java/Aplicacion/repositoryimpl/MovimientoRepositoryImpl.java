package Aplicacion.repositoryimpl;

import Dominio.Modelo.MovimientoAlmacen;
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

public class MovimientoRepositoryImpl implements CrudGenerico<MovimientoAlmacen, Integer> {

    private final ProductoRepositoryImpl productoRepository = new ProductoRepositoryImpl();

    @Override
    public int save(MovimientoAlmacen beans) {
        String sql = "INSERT INTO movimiento_almacen (id_producto, tipo_movimiento, cantidad, fecha, contexto) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, beans.getProducto().getIdProducto());
            pstmt.setString(2, beans.getTipoMovimiento());
            pstmt.setDouble(3, beans.getCantidad());
            pstmt.setDate(4, beans.getFecha());
            pstmt.setString(5, beans.getContexto());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(MovimientoAlmacen beans) {
        String sql = "UPDATE movimiento_almacen "
                + "SET id_producto = ?, tipo_movimiento = ?, cantidad = ?, fecha = ?, contexto = ? "
                + "WHERE id_movimiento = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, beans.getProducto().getIdProducto());
            pstmt.setString(2, beans.getTipoMovimiento());
            pstmt.setDouble(3, beans.getCantidad());
            pstmt.setDate(4, beans.getFecha());
            pstmt.setString(5, beans.getContexto());
            pstmt.setInt(6, beans.getIdMovimiento());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM movimiento_almacen WHERE id_movimiento = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<MovimientoAlmacen> findById(Integer id) {
        String sql = "SELECT * FROM movimiento_almacen WHERE id_movimiento = ?";

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
    public List<MovimientoAlmacen> findAll() {
        List<MovimientoAlmacen> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimiento_almacen";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }

            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveAndFindId(MovimientoAlmacen beans) {
        String sql = "INSERT INTO movimiento_almacen (id_producto, tipo_movimiento, cantidad, fecha, contexto) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, beans.getProducto().getIdProducto());
            pstmt.setString(2, beans.getTipoMovimiento());
            pstmt.setDouble(3, beans.getCantidad());
            pstmt.setDate(4, beans.getFecha());
            pstmt.setString(5, beans.getContexto());

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

    private MovimientoAlmacen mapear(ResultSet rs) throws SQLException {
        return new MovimientoAlmacen(
                rs.getInt("id_movimiento"),
                productoRepository.findById(rs.getInt("id_producto")).orElse(null),
                rs.getString("tipo_movimiento"),
                rs.getDouble("cantidad"),
                rs.getDate("fecha"),
                rs.getString("contexto")
        );
    }
}
