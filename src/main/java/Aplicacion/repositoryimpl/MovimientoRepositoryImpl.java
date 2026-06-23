package Aplicacion.repositoryimpl;

import Dominio.Modelo.Movimiento;
import Dominio.repository.CrudGenerico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimientoRepositoryImpl implements CrudGenerico<Movimiento, Integer> {

    private ProductoRepositoryImpl productoRepository = new ProductoRepositoryImpl();

    @Override
    public int save(Movimiento beans) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int respuesta = -1;

        try {

            String sql = """
                    INSERT INTO movimiento_almacen
                    (id_producto,tipo_movimiento,cantidad,fecha,contexto)
                    VALUES(?,?,?,?,?)
                    """;

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, beans.getProducto().getIdProducto());
            pstmt.setString(2, beans.getTipoMovimiento());
            pstmt.setDouble(3, beans.getCantidad());
            pstmt.setDate(4, beans.getFecha());
            pstmt.setString(5, beans.getContexto());

            respuesta = pstmt.executeUpdate();

            return respuesta;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int update(Movimiento beans) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int respuesta = -1;

        try {

            String sql = """
                    UPDATE movimiento_almacen
                    SET id_producto=?,
                        tipo_movimiento=?,
                        cantidad=?,
                        fecha=?,
                        contexto=?
                    WHERE id_movimiento=?
                    """;

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, beans.getProducto().getIdProducto());
            pstmt.setString(2, beans.getTipoMovimiento());
            pstmt.setDouble(3, beans.getCantidad());
            pstmt.setDate(4, beans.getFecha());
            pstmt.setString(5, beans.getContexto());
            pstmt.setInt(6, beans.getIdMovimiento());

            respuesta = pstmt.executeUpdate();

            return respuesta;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int delete(Integer id) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int respuesta = -1;

        try {

            String sql = """
                    DELETE FROM movimiento_almacen
                    WHERE id_movimiento=?
                    """;

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            respuesta = pstmt.executeUpdate();

            return respuesta;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Movimiento findById(Integer id) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            String sql = """
                    SELECT * FROM movimiento_almacen
                    WHERE id_movimiento=?
                    """;

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                return new Movimiento(
                        rs.getInt("id_movimiento"),
                        productoRepository.findById(rs.getInt("id_producto")),
                        rs.getString("tipo_movimiento"),
                        rs.getDouble("cantidad"),
                        rs.getDate("fecha"),
                        rs.getString("contexto")
                );
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Movimiento> findAll() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Movimiento> lista = new ArrayList<>();

        try {

            String sql = """
                    SELECT * FROM movimiento_almacen
                    """;

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                lista.add(
                        new Movimiento(
                                rs.getInt("id_movimiento"),
                                productoRepository.findById(rs.getInt("id_producto")),
                                rs.getString("tipo_movimiento"),
                                rs.getDouble("cantidad"),
                                rs.getDate("fecha"),
                                rs.getString("contexto")
                        )
                );
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
    
    
    
    
    
}
