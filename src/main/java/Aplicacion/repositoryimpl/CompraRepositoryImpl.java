package Aplicacion.repositoryimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.Modelo.Compra;
import Dominio.Modelo.DetalleCompra;
import Dominio.Modelo.Proveedor;
import Dominio.repository.CompraRepository;
import Dominio.repository.DetalleCompraRepository;
import Dominio.repository.ProveedorRepository;
import Presentacion.Principal.ConexionPostgresSQL;

public class CompraRepositoryImpl implements CompraRepository {
    Connection conexion;
    private final ProveedorRepository proveedorRepository;
    private final DetalleCompraRepository detalleCompraRepository;

    public CompraRepositoryImpl() {
        this.conexion = ConexionPostgresSQL.getConexion();
        this.proveedorRepository = new ProveedorRepositoryImpl();
        this.detalleCompraRepository = new DetalleCompraRepositoryImpl();
    }

    @Override
    public int save(DetalleCompra detalleCompra) {
        int resultado = -1;
        int total = 0;
        try {
            String sql = """
                    INSERT INTO compra(id_proveedor,fecha,total)VALUES
                    (?,?,?) RETURNING *;
                    """;
            for (int i = 0; i < detalleCompra.getProductos().size(); i++) {
                total += (int) (detalleCompra.getProductos().get(i).getPrecio() * detalleCompra.getCantidad().get(i));
            }
            detalleCompra.getCompra().setTotal(total);

            PreparedStatement preparar = conexion.prepareStatement(sql);

            preparar.setInt(1, detalleCompra.getCompra().getProveedor().getIdProveedor());

            preparar.setDate(2, detalleCompra.getCompra().getFecha());

            preparar.setDouble(3, detalleCompra.getCompra().getTotal());

           resultado= preparar.executeUpdate();

            int resultadoDeDetalle= detalleCompraRepository.save(detalleCompra);
            if(resultadoDeDetalle>0&& resultado>0){
                return resultado;
            }else{
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Compra> listarCompras() {
        List<Compra> compras = new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM compra
                    """;

            PreparedStatement preparar = conexion.prepareStatement(sql);
            ResultSet resultado = preparar.executeQuery();
            while (resultado.next()) {
                Proveedor p = new Proveedor();
                p = proveedorRepository.buscarPorId(
                        resultado.getInt("id_proveedor"));

                compras.add(new Compra(
                        resultado.getInt("id_compra"),
                        resultado.getDate("fecha"),
                        p,
                        resultado.getDouble("total")));
            }
            return compras;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Compra> listarComprasMasAltos() {

        List<Compra> comprasMasAltos = new ArrayList<>();
        try {
            String sql = """
                            SELECT  * FROM compra
                            ORDER BY total DESC
                            LIMIT 5;
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            ResultSet resultado = preparar.executeQuery();
            while (resultado.next()) {
                comprasMasAltos.add(new Compra(
                        resultado.getInt("id_compra"),
                        resultado.getDate("fecha"),
                        proveedorRepository.buscarPorId(resultado.getInt("id_proveedor")),
                        resultado.getDouble("total")));
            }
            return comprasMasAltos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Compra> listarComprasPorFecha(Date fecha, Date fecha2) {
        List<Compra> compras = new ArrayList<>();
        try {
            String sql = """
                            SELECT  * FROM compra
                           WHERE fecha BETWEEN ? AND ?
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setDate(1, fecha);
            preparar.setDate(2, fecha2);
            ResultSet resultado = preparar.executeQuery();
            while (resultado.next()) {
                compras.add(new Compra(
                        resultado.getInt("id_compra"),
                        resultado.getDate("fecha"),
                        proveedorRepository.buscarPorId(resultado.getInt("id_proveedor")),
                        resultado.getDouble("total")

                ));
            }
            return compras;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Compra traerCompraPorId(Integer id) {
        try {
            String sql = """
                            SELECT * FROM compra
                            WHERE id_compra=?
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setInt(1, id);
            ResultSet resultado = preparar.executeQuery();
            if (resultado.next()) {
                return new Compra(
                        resultado.getInt("id_compra"),
                        resultado.getDate("fecha"),
                        proveedorRepository.buscarPorId(resultado.getInt("id_proveedor")),
                        resultado.getDouble("total"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
