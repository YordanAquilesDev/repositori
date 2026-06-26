package Aplicacion.repositoryimpl;

import Dominio.Modelo.Pedido;
import Dominio.repository.CrudGenerico;
import Presentacion.Principal.ConexionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoRepositoryImpl implements CrudGenerico<Pedido, Integer> {

    private final ClienteRepositoryImpl clienteRepository;

    public PedidoRepositoryImpl() {
        this.clienteRepository = new ClienteRepositoryImpl();
    }

    @Override
    public int save(Pedido beans) {
        String sql = "INSERT INTO pedido (id_cliente, fecha, estado, total) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, beans.getCliente().getIdCliente());
            pstmt.setDate(2, beans.getFecha());
            pstmt.setString(3, beans.getEstado());
            pstmt.setDouble(4, beans.getTotal());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Pedido beans) {
        String sql = "UPDATE pedido SET id_cliente = ?, fecha = ?, estado = ?, total = ? WHERE id_pedido = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, beans.getCliente().getIdCliente());
            pstmt.setDate(2, beans.getFecha());
            pstmt.setString(3, beans.getEstado());
            pstmt.setDouble(4, beans.getTotal());
            pstmt.setInt(5, beans.getIdPedido());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM pedido WHERE id_pedido = ?";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Pedido> findById(Integer id) {
        String sql = "SELECT * FROM pedido WHERE id_pedido = ?";

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
    public List<Pedido> findAll() {
        List<Pedido> list = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

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
    public int saveAndFinId(Pedido beans) {
        String sql = "INSERT INTO pedido (id_cliente, fecha, estado, total) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConexionMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, beans.getCliente().getIdCliente());
            pstmt.setDate(2, beans.getFecha());
            pstmt.setString(3, beans.getEstado());
            pstmt.setDouble(4, beans.getTotal());

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

    private Pedido mapear(ResultSet rs) throws SQLException {
        return new Pedido(
                rs.getInt("id_pedido"),
                rs.getDate("fecha"),
                clienteRepository.findById(rs.getInt("id_cliente")).orElse(null),
                rs.getString("estado"),
                rs.getDouble("total")
        );
    }
}
