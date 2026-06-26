package Aplicacion.repositoryimpl;

import Dominio.Modelo.Producto;
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

public class ProductoRepositoryImpl implements CrudGenerico<Producto, Integer> {

    @Override
    public int save(Producto beans) {
        String sql = "INSERT INTO producto (nombre, tipo_producto, unidad_medida, precio_unidad, stock_actual) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, beans.getNombre());
            pstmt.setString(2, beans.getTipoProducto());
            pstmt.setString(3, beans.getUnidadMedida());
            pstmt.setDouble(4, beans.getPrecioUnidad());
            pstmt.setDouble(5, beans.getStockActual());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Producto beans) {
        String sql = "UPDATE producto SET nombre = ?, tipo_producto = ?, unidad_medida = ?, "
                + "precio_unidad = ?, stock_actual = ? WHERE id_producto = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, beans.getNombre());
            pstmt.setString(2, beans.getTipoProducto());
            pstmt.setString(3, beans.getUnidadMedida());
            pstmt.setDouble(4, beans.getPrecioUnidad());
            pstmt.setDouble(5, beans.getStockActual());
            pstmt.setInt(6, beans.getIdProducto());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        String sql = "SELECT * FROM producto WHERE id_producto = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Producto(
                            rs.getInt("id_producto"),
                            rs.getDouble("stock_actual"),
                            rs.getString("unidad_medida"),
                            rs.getString("nombre"),
                            rs.getString("tipo_producto"),
                            rs.getDouble("precio_unidad")
                    ));
                }
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> list = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getDouble("stock_actual"),
                        rs.getString("unidad_medida"),
                        rs.getString("nombre"),
                        rs.getString("tipo_producto"),
                        rs.getDouble("precio_unidad")
                ));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveAndFinId(Producto beans) {
        String sql = "INSERT INTO producto (nombre, tipo_producto, unidad_medida, precio_unidad, stock_actual) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, beans.getNombre());
            pstmt.setString(2, beans.getTipoProducto());
            pstmt.setString(3, beans.getUnidadMedida());
            pstmt.setDouble(4, beans.getPrecioUnidad());
            pstmt.setDouble(5, beans.getStockActual());

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

}
