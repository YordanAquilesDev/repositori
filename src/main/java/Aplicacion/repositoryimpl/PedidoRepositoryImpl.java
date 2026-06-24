package Aplicacion.repositoryimpl;

import Dominio.Modelo.Pedido;
import Dominio.repository.CrudGenerico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepositoryImpl implements CrudGenerico<Pedido, Integer> {

    // Inyectamos el repositorio Cliente para obtener el objeto Cliente
    private ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();

    @Override
    public int save(Pedido beans) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int respuesta = -1;

        try {
            String sql = """
                    INSERT INTO pedido(id_cliente,fecha,estado,total)
                    VALUES(?,?,?,?)
                    """;

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, beans.getCliente().getIdCliente());
            pstmt.setDate(2, (Date) beans.getFecha());
            pstmt.setString(3, beans.getEstado());
            pstmt.setDouble(4, beans.getTotal());

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
    public int update(Pedido beans) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int respuesta = -1;

        try {
            String sql = """
                    UPDATE pedido
                    SET id_cliente=?, fecha=?, estado=?, total=?
                    WHERE id_pedido=?
                    """;

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, beans.getCliente().getIdCliente());
            pstmt.setDate(2, (Date) beans.getFecha());
            pstmt.setString(3, beans.getEstado());
            pstmt.setDouble(4, beans.getTotal());
            pstmt.setInt(5, beans.getIdPedido());

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
                    DELETE FROM pedido
                    WHERE id_pedido=?
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
    public Pedido findById(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = """
                    SELECT * FROM pedido
                    WHERE id_pedido=?
                    """;

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Pedido(
    rs.getInt("id_pedido"),
    rs.getDate("fecha"),
    clienteRepository.findById(rs.getInt("id_cliente")),
    rs.getString("estado"),
    rs.getDouble("total")
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
    public List<Pedido> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Pedido> lista = new ArrayList<>();

        try {
            String sql = """
                    SELECT * FROM pedido
                    """;

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                lista.add(
                        new Pedido(
                                rs.getInt("id_pedido"),
                                rs.getDate("fecha"),
                                clienteRepository.findById(rs.getInt("id_cliente")),
                                rs.getString("estado"),
                                rs.getDouble("total")
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

    @Override
    public int saveAndFinId(Pedido beans) {
        return 0;
    }
}