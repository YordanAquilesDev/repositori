package Aplicacion.repositoryimpl;

import Dominio.Modelo.Compra;
import Dominio.repository.CrudGenerico;
import Presentacion.Principal.ConexionMySQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompraRepositoryImpl implements CrudGenerico<Compra, Integer> {

    private final ProveedorRepositoryImpl proveedorRepository;

    public CompraRepositoryImpl() {
        this.proveedorRepository = new ProveedorRepositoryImpl();
    }

    @Override
    public int save(Compra beans) {
        String sql = "INSERT INTO compra (id_proveedor, fecha, total) VALUES (?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, beans.getProveedor().getIdProveedor());
            pstmt.setDate(2, beans.getFecha());
            pstmt.setDouble(3, beans.getTotal());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Compra beans) {
        String sql = "UPDATE compra SET id_proveedor = ?, fecha = ?, total = ? WHERE id_compra = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, beans.getProveedor().getIdProveedor());
            pstmt.setDate(2, beans.getFecha());
            pstmt.setDouble(3, beans.getTotal());
            pstmt.setInt(4, beans.getIdCompra());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM compra WHERE id_compra = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Compra> findById(Integer id) {
        String sql = "SELECT * FROM compra WHERE id_compra = ?";

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
    public List<Compra> findAll() {
        List<Compra> list = new ArrayList<>();
        String sql = "SELECT * FROM compra";

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
    public int saveAndFinId(Compra beans) {
        String sql = "INSERT INTO compra (id_proveedor, fecha, total) VALUES (?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, beans.getProveedor().getIdProveedor());
            pstmt.setDate(2, beans.getFecha());
            pstmt.setDouble(3, beans.getTotal());

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

    public Compra traerCompraPorId(Integer id) {
        return findById(id).orElse(null);
    }

    public List<Compra> listarComprasMasAltos() {
        List<Compra> comprasMasAltos = new ArrayList<>();
        String sql = "SELECT * FROM compra ORDER BY total DESC LIMIT 5";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                comprasMasAltos.add(mapear(rs));
            }

            return comprasMasAltos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Compra> listarComprasPorFecha(Date fecha, Date fecha2) {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra WHERE fecha BETWEEN ? AND ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, fecha);
            pstmt.setDate(2, fecha2);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    compras.add(mapear(rs));
                }
            }

            return compras;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Compra mapear(ResultSet rs) throws SQLException {
        return new Compra(
                rs.getInt("id_compra"),
                rs.getDate("fecha"),
                proveedorRepository.findById(rs.getInt("id_proveedor")).orElse(null),
                rs.getDouble("total")
        );
    }
}
