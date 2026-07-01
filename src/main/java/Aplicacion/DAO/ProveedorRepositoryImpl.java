package Aplicacion.DAO;

import Dominio.Modelo.Proveedor;
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

public class ProveedorRepositoryImpl implements CrudGenerico<Proveedor, Integer> {

    @Override
    public int save(Proveedor beans) {
        String sql = "INSERT INTO proveedor (nombre, apellido, dni, telefono) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, beans.getNombre());
            pstmt.setString(2, beans.getApellido());
            pstmt.setString(3, beans.getDni());
            pstmt.setString(4, beans.getTelefono());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Proveedor beans) {
        String sql = "UPDATE proveedor SET nombre = ?, apellido = ?, dni = ?, telefono = ? WHERE id_proveedor = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, beans.getNombre());
            pstmt.setString(2, beans.getApellido());
            pstmt.setString(3, beans.getDni());
            pstmt.setString(4, beans.getTelefono());
            pstmt.setInt(5, beans.getIdProveedor());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM proveedor WHERE id_proveedor = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Proveedor> findById(Integer id) {
        String sql = "SELECT * FROM proveedor WHERE id_proveedor = ?;";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Proveedor(
                            rs.getInt("id_proveedor"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("dni"),
                            rs.getString("telefono")
                    ));
                }
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Proveedor> findAll() {
        List<Proveedor> list = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Proveedor(
                        rs.getInt("id_proveedor"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("telefono")
                ));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveAndFindId(Proveedor beans) {
        String sql = "INSERT INTO proveedor (nombre, apellido, dni, telefono) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, beans.getNombre());
            pstmt.setString(2, beans.getApellido());
            pstmt.setString(3, beans.getDni());
            pstmt.setString(4, beans.getTelefono());

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

    public Proveedor buscarPorId(long id) {
        return findById((int) id).orElse(null);
    }

    public List<Proveedor> listarProveedores() {
        return findAll();
    }
}
