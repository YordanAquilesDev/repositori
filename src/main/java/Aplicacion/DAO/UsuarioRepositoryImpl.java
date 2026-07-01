package Aplicacion.DAO;

import Dominio.Modelo.Usuario;
import Dominio.repository.CrudGenerico;
import Aplicacion.utils.ConexionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryImpl implements CrudGenerico<Usuario, Integer> {

    @Override
    public int save(Usuario beans) {
        String sql = "INSERT INTO usuarios (username, password, email, rol, nombre, apellido, dni, celular, direccion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, beans.getUsername());
            pstmt.setString(2, beans.getPassword());
            pstmt.setString(3, beans.getEmail());
            pstmt.setString(4, beans.getRol());
            pstmt.setString(5, beans.getNombre());
            pstmt.setString(6, beans.getApellido());
            pstmt.setString(7, beans.getDni());
            pstmt.setString(8, beans.getCelular());
            pstmt.setString(9, beans.getDireccion());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Usuario beans) {
        String sql = "UPDATE usuarios SET username = ?, password = ?, email = ?, rol = ?, "
                + "nombre = ?, apellido = ?, dni = ?, celular = ?, direccion = ?, estado = ? "
                + "WHERE id_usuario = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, beans.getUsername());
            pstmt.setString(2, beans.getPassword());
            pstmt.setString(3, beans.getEmail());
            pstmt.setString(4, beans.getRol());
            pstmt.setString(5, beans.getNombre());
            pstmt.setString(6, beans.getApellido());
            pstmt.setString(7, beans.getDni());
            pstmt.setString(8, beans.getCelular());
            pstmt.setString(9, beans.getDireccion());
            pstmt.setString(10, beans.getEstado());
            pstmt.setInt(11, beans.getIdUsuario());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";

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
    public List<Usuario> findAll() {
        List<Usuario> list = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

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
    public int saveAndFindId(Usuario beans) {
        String sql = "INSERT INTO usuarios (username, password, email, rol, nombre, apellido, dni, celular, direccion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, beans.getUsername());
            pstmt.setString(2, beans.getPassword());
            pstmt.setString(3, beans.getEmail());
            pstmt.setString(4, beans.getRol());
            pstmt.setString(5, beans.getNombre());
            pstmt.setString(6, beans.getApellido());
            pstmt.setString(7, beans.getDni());
            pstmt.setString(8, beans.getCelular());
            pstmt.setString(9, beans.getDireccion());

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

    public Usuario login(String username, String password) {
        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ? AND estado = 'ACTIVO'";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("rol"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("dni"),
                rs.getString("celular"),
                rs.getString("direccion"),
                rs.getDate("fecha_registro"),
                rs.getString("estado")
        );
    }
}
