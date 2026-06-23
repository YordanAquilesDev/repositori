package Aplicacion.repositoryimpl;

import Aplicacion.ServiceImpl.ClienteServiceImpl;
import Dominio.Modelo.Pedido;
import Presentacion.Principal.ConexionPostgresSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepositoryImpl implements PedidoRepository {

    Connection conexion;
    private final ClienteService clienteService;


    public PedidoRepositoryImpl() {
        this.conexion = ConexionPostgresSQL.getConexion();
        this.clienteService = new ClienteServiceImpl();
    }

    @Override
    public List<Pedido> listarPedidos() {
        List<Pedido> listaPedidos = new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM pedido
                    """;

            PreparedStatement preparar = conexion.prepareStatement(sql);
            ResultSet resultado = preparar.executeQuery();
            while (resultado.next()) {
                listaPedidos.add(new Pedido(
                        resultado.getInt("id_pedido"),
                        resultado.getDate("fecha"),
                        clienteService.finById(resultado.getInt("id_cliente")),
                        resultado.getString("estado"),
                        resultado.getDouble("total")
                ));

            }
            return listaPedidos;

        } catch (SQLException e) {
            System.out.println("ERROR EN "+ e);
            throw new RuntimeException(e);
        }catch(Exception e){
             System.out.println("ERROR EN "+ e);
            return null;
        }
    }

    @Override
    public Pedido ActualizarPedido(Pedido pedido) {
        return null;
    }

    @Override
    public List<Pedido> listarPedidosEntregados() {
        List<Pedido> listaPedidosEntregados = new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM Pedido
                    WHERE estado='Entregado'
            """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            ResultSet resultado = preparar.executeQuery();
            while (resultado.next()) {
                listaPedidosEntregados.add(new Pedido(
                        resultado.getInt("id_pedido"),
                        resultado.getDate("fecha"),
                        clienteService.finById(resultado.getInt("id_cliente")),
                        resultado.getString("estado"),
                        resultado.getDouble("total")
                ));

            }
            return listaPedidosEntregados;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Pedido> listarPedidosNoEntregados() {
        List<Pedido> listaPedidosNoEntregados = new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM Pedido
                    WHERE estado='No Entregado'
            """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            ResultSet resultado = preparar.executeQuery();
            while (resultado.next()) {
                listaPedidosNoEntregados.add(new Pedido(
                        resultado.getInt("id_pedido"),
                        resultado.getDate("fecha"),
                        clienteService.finById(resultado.getInt("id_cliente")),
                        resultado.getString("estado"),
                        resultado.getDouble("total")
                ));

            }
            return listaPedidosNoEntregados;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
