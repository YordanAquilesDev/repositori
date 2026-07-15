package Aplicacion.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Dominio.Modelo.Venta;
import Dominio.repository.ICRUD;
import Aplicacion.utils.ConexionMySQL;

public class VentaDAO implements ICRUD<Venta, Integer> {

    public VentaDAO() {
    }
    @Override
    public int save(Venta beans) {
        Connection conexion = null;
    PreparedStatement ps = null;

    int resultado = 0;

    try {

        String sql = """
                INSERT INTO Venta(idCliente, fecha, total)
                VALUES(?,?,?)
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        ps.setInt(1, beans.getIdCliente());
        ps.setString(2, beans.getFecha());
        ps.setDouble(3, beans.getTotal());

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
    public int update(Venta beans) {
        Connection conexion = null;
    PreparedStatement ps = null;

    int resultado = 0;

    try {

        String sql = """
                UPDATE Venta
                SET idCliente = ?, fecha = ?, total = ?
                WHERE idVenta = ?
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        ps.setInt(1, beans.getIdCliente());
        ps.setString(2, beans.getFecha());
        ps.setDouble(3, beans.getTotal());
        ps.setInt(4, beans.getIdVenta());

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
                DELETE FROM Venta
                WHERE idVenta = ?
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
    public Optional<Venta> findById(Integer id) {
        Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {

        String sql = """
                SELECT *
                FROM Venta
                WHERE idVenta = ?
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        ps.setInt(1, id);

        rs = ps.executeQuery();

        if (rs.next()) {

            Venta venta = new Venta();

            venta.setIdVenta(rs.getInt("idVenta"));
            venta.setIdCliente(rs.getInt("idCliente"));
            venta.setFecha(rs.getString("fecha"));
            venta.setTotal(rs.getDouble("total"));

            return Optional.of(venta);
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
    public List<Venta> findAll() {
        Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    List<Venta> lista = new ArrayList<>();

    try {

        String sql = """
                SELECT *
                FROM Venta
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        rs = ps.executeQuery();

        while (rs.next()) {

            Venta venta = new Venta();

            venta.setIdVenta(rs.getInt("idVenta"));
            venta.setIdCliente(rs.getInt("idCliente"));
            venta.setFecha(rs.getString("fecha"));
            venta.setTotal(rs.getDouble("total"));

            lista.add(venta);
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
    public int saveAndFindId(Venta beans) {
        Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {

        String sql = """
                INSERT INTO Venta(idCliente, fecha, total)
                VALUES(?,?,?)
                """;

        conexion = ConexionMySQL.getConexion();

        ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setInt(1, beans.getIdCliente());
        ps.setString(2, beans.getFecha());
        ps.setDouble(3, beans.getTotal());

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
}

