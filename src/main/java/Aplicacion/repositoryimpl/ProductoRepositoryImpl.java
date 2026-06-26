package Aplicacion.repositoryimpl;

import Dominio.Modelo.Producto;
import Dominio.repository.CrudGenerico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements CrudGenerico<Producto, Integer> {

    @Override
    public int save(Producto beans) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int respuesta = -1;

        try {
            String sql = """
                    INSERT INTO producto(nombre,tipo_producto,unidad_medida,precio_unidad,stock_actual)
                    VALUES(?,?,?,?,?)
                    """;

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, beans.getNombre());
            pstmt.setString(2, beans.getTipoProducto());
            pstmt.setString(3, beans.getUnidadMedida());
            pstmt.setDouble(4, beans.getPrecioUnidad());
            pstmt.setDouble(5, beans.getStockActual());

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
    public int update(Producto beans) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int respuesta = -1;

        try {
            String sql = """
                    UPDATE producto
                    SET nombre=?, tipo_producto=?, unidad_medida=?,
                        precio_unidad=?, stock_actual=?
                    WHERE id_producto=?
                    """;

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, beans.getNombre());
            pstmt.setString(2, beans.getTipoProducto());
            pstmt.setString(3, beans.getUnidadMedida());
            pstmt.setDouble(4, beans.getPrecioUnidad());
            pstmt.setDouble(5, beans.getStockActual());
            pstmt.setInt(6, beans.getIdProducto());

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
                    DELETE FROM producto
                    WHERE id_producto=?
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
    public Producto findById(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = """
                    SELECT * FROM producto
                    WHERE id_producto=?
                    """;

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("tipo_producto"),
                        rs.getString("unidad_medida"),
                        rs.getDouble("precio_unidad"),
                        rs.getDouble("stock_actual")
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
    public List<Producto> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Producto> list = new ArrayList<>();

        try {
            String sql = """
                    SELECT * FROM producto
                    """;

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(
                        new Producto(
                                rs.getInt("id_producto"),
                                rs.getString("nombre"),
                                rs.getString("tipo_producto"),
                                rs.getString("unidad_medida"),
                                rs.getDouble("precio_unidad"),
                                rs.getDouble("stock_actual")
                        )
                );
            }

            return list;

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
