package Aplicacion.DAO;

import Dominio.Modelo.Usuario;
import Dominio.repository.ICRUD;
import Aplicacion.utils.ConexionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository implements ICRUD<Usuario, Integer> {

    @Override
    public int save(Usuario beans) {
         Connection conexion = null;
    PreparedStatement ps = null;

    int resultado = 0;

    try {

        String sql = """
                INSERT INTO Usuario(idRol, nombre, correo, password, estado)
                VALUES(?,?,?,?,?)
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        ps.setInt(1, beans.getIdRol());
        ps.setString(2, beans.getNombre());
        ps.setString(3, beans.getCorreo());
        ps.setString(4, beans.getPassword());
        ps.setBoolean(5, beans.isEstado());

        resultado = ps.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        try {
            if (ps != null) ps.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    return resultado;
    }

    @Override
    public int update(Usuario beans) {
        Connection conexion = null;
    PreparedStatement ps = null;

    int resultado = 0;

    try {

        String sql = """
                UPDATE Usuario
                SET idRol = ?, nombre = ?, correo = ?, password = ?, estado = ?
                WHERE idUsuario = ?
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        ps.setInt(1, beans.getIdRol());
        ps.setString(2, beans.getNombre());
        ps.setString(3, beans.getCorreo());
        ps.setString(4, beans.getPassword());
        ps.setBoolean(5, beans.isEstado());
        ps.setInt(6, beans.getIdUsuario());

        resultado = ps.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        try {
            if (ps != null) ps.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    return resultado;
    }

    @Override
    public int delete(Integer id) {
        Connection conexion = null;
    PreparedStatement ps = null;

    int resultado = 0;

    try {

        String sql = """
                DELETE FROM Usuario
                WHERE idUsuario = ?
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        ps.setInt(1, id);

        resultado = ps.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        try {
            if (ps != null) ps.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    return resultado;
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {

        String sql = """
                SELECT *
                FROM Usuario
                WHERE idUsuario = ?
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        ps.setInt(1, id);

        rs = ps.executeQuery();

        if (rs.next()) {

            Usuario usuario = new Usuario();

            usuario.setIdUsuario(rs.getInt("idUsuario"));
            usuario.setIdRol(rs.getInt("idRol"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setPassword(rs.getString("password"));
            usuario.setEstado(rs.getBoolean("estado"));

            return Optional.of(usuario);
        }

        return Optional.empty();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

    @Override
    public List<Usuario> findAll() {
        Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    List<Usuario> lista = new ArrayList<>();

    try {

        String sql = """
                SELECT *
                FROM Usuario
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        rs = ps.executeQuery();

        while (rs.next()) {

            Usuario usuario = new Usuario();

            usuario.setIdUsuario(rs.getInt("idUsuario"));
            usuario.setIdRol(rs.getInt("idRol"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setPassword(rs.getString("password"));
            usuario.setEstado(rs.getBoolean("estado"));

            lista.add(usuario);
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    return lista;
    }

    @Override
    public int saveAndFindId(Usuario beans) {
         Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {

        String sql = """
                INSERT INTO Usuario(idRol, nombre, correo, password, estado)
                VALUES(?,?,?,?,?)
                """;

        conexion = ConexionMySQL.getConexion();

        ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setInt(1, beans.getIdRol());
        ps.setString(2, beans.getNombre());
        ps.setString(3, beans.getCorreo());
        ps.setString(4, beans.getPassword());
        ps.setBoolean(5, beans.isEstado());

        ps.executeUpdate();

        rs = ps.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            rs.getInt("idUsuario"),
            rs.getInt("idRol"),
            rs.getString("nombre"),
            rs.getString("correo"),
            rs.getString("password"),
            rs.getBoolean("estado")
    );
    }
}
