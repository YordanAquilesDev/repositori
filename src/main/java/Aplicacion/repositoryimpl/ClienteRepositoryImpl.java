package Aplicacion.repositoryimpl;

import Dominio.Modelo.Cliente;
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

public class ClienteRepositoryImpl implements CrudGenerico<Cliente, Integer> {

    @Override
    public int save(Cliente beans) {
        String sql = "INSERT INTO cliente (nombre, apellido, dni, celular, direccion) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, beans.getNombre());
            pstmt.setString(2, beans.getApellido());
            pstmt.setString(3, beans.getDni());
            pstmt.setString(4, beans.getCelular());
            pstmt.setString(5, beans.getDireccion());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Cliente beans) {
        String sql = "UPDATE cliente "
                + "SET nombre = ?, apellido = ?, dni = ?, celular = ?, direccion = ? "
                + "WHERE id_cliente = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, beans.getNombre());
            pstmt.setString(2, beans.getApellido());
            pstmt.setString(3, beans.getDni());
            pstmt.setString(4, beans.getCelular());
            pstmt.setString(5, beans.getDireccion());
            pstmt.setInt(6, beans.getIdCliente());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("dni"),
                            rs.getString("celular"),
                            rs.getString("direccion")
                    ));
                }
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> list = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("celular"),
                        rs.getString("direccion")
                ));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveAndFinId(Cliente beans) {
        String sql = "INSERT INTO cliente (nombre, apellido, dni, celular, direccion) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, beans.getNombre());
            pstmt.setString(2, beans.getApellido());
            pstmt.setString(3, beans.getDni());
            pstmt.setString(4, beans.getCelular());
            pstmt.setString(5, beans.getDireccion());

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
