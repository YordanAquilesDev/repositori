package Aplicacion.repositoryimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.Modelo.DetalleCompra;
import Dominio.Modelo.Producto;
import Presentacion.Principal.ConexionPostgresSQL;

public class DetalleCompraRepositoryImpl implements CrudGenerico<DetalleCompra,Integer>{
    private final ProductoServiceImpl productoService;
    private final CompraServiceImpl compraService;
    public DetalleCompraRepositoryImpl() {
        this.productoService = new ProductoRepositoryImpl();
        this.compraService= new CompraRepositoryImpl();
    }

    @Override
    public int  save(DetalleCompra beans) {
        Connection conn=null;
        PreparedStatement preparar= null;
        try {
                String sql = """
                INSERT INTO detalle_compra (id_compra, id_producto, cantidad, subtotal)
                VALUES (?, ?, ?, ?) RETURNING *
                """;
                conn= ConexionMySQL.getConexionMySQL();
                preparar= conn.prepareStatement(sql);
                 preparar.setInt(1,beans.getCompra.getIdCompra());
                 preparar.setInt(2,beans.getProducto().getIdProducto());
                 preparar.setInt(3,beans.getCantidad());
                 preparar.seDouble(4,beans.getSubTotal());

                 return preparar.executeUpdate();
             
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el detalle de compra", e);
        } finally{
             try{
                if(conn!= null) conn.close();
                if(preparar!=  null) preparar.close();
             } catch(Exception error){
                error.printStackTrace();
             }
        }
    }

    public List<DetalleCompra> listarPorFecha(Date fecha, Date fecha2) {
        List<DetalleCompra> detalles = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();
        List<Double > subtotals = new ArrayList<>();
        try {
            String sql = """
                    selet * from detalle_compra
                    where fecha between ? and ?;

                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setDate(1, fecha);
            preparar.setDate(2, fecha2);
            ResultSet rs = preparar.executeQuery();
            int temp = 1;
            while (rs.next()) {
                productos.add(productoRepository.buscarPorId(rs.getInt("id_producto")));
                cantidades.add(rs.getInt("cantidad"));
                subtotals.add(rs.getDouble("subtotal"));
                if (temp < rs.getInt("id_detalle")) {
                    detalles.add(new DetalleCompra(
                            rs.getInt("id_detalle"),
                           null,
                           // compraRepository.traerCompraPorId(rs.getInt("id_compra")),
                            productos,
                            cantidades,
                            subtotals));

                    temp = rs.getInt("id_detalle");
                    productos = new ArrayList<>();
                    cantidades = new ArrayList<>();
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<DetalleCompra>  finById(Integer id){
        Connection conn = null;
        PreparedStatement preparar= null;
        ResultSet resultado= null;
        List<DetalleCompra> detallePorId= new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM detalle_compra
                    WHERE id_detalle= ?
                    """;
            conn= ConexionMySQL.getConexionMySQL();
            PreparedStatement preparar = conn.prepareStatement(sql);
            preparar.setInt(1, id);
            ResultSet resultado = preparar.executeQuery();
            int idDetalle=0;
            while(resultado.next()){
               detallePorId.add(new DetalleCompra(
                 resultado.getInt(1),
                 compraService.finById(resultado.getInt(2)),
                 productoService.finById(resultado.getInt(3)),
                 resultado.getInt(4),
                 resultado.getDouble(5)
               ));
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try{
                if(conn!= null) conn.close();
                if(preparar!= null) preparar.close();
                if(resultado!= null) resultado.close();
            }catch(Exception error){
                error.printStackTrace();
             }
        }
        return null;


    }

    public DetalleCompra ObtenerPorId(Long id) {
        Connection conn = null;
        PreparedStatement preparar= null;
        ResultSet resultado= null;
        List<Producto> productos = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();
        List<Double > subtotals = new ArrayList<>();
        DetalleCompra detalleCompra= new DetalleCompra();;
        try {
            String sql = """
                    SELECT * FROM detalle_compra
                    WHERE id_detalle= ?
                    """;
                    conn= ConexionMySQL.getConexionMySQL();
            PreparedStatement preparar = conn.prepareStatement(sql);
            preparar.setInt(1, id);
            ResultSet resultado = preparar.executeQuery();
            int idDetalle=0;
            while(resultado.next()){
                idDetalle= resultado.getInt(1);
                 productos.add(productoRepository.buscarPorId(rs.getInt("id_producto")));
                
    

            }
        
            if (rs.next()) {
                while
                productos.add(productoRepository.buscarPorId(rs.getInt("id_producto")));
                cantidades.add(rs.getInt("cantidad"));
                subtotals.add(rs.getDouble("subtotal"));
                if (rs.next()) {
                    return new DetalleCompra(
                            rs.getInt("id_detalle"),
                            null,// entero
                           // compraRepository.traerCompraPorId(rs.getInt("id_compra")),
                            productos,
                            cantidades,
                            subtotals);

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     
     @Override
     public  List<DetalleCompra> finAll(){
        Connection conn = null;
        PreparedStatement preparar= null;
        ResultSet resultado= null;
        List<DetalleCompra> lista= null;
          try {
            String sql = """
                    SELECT * FROM detalle_compra
                    """;
            conn= ConexionMySQL.getConexionMySQL();
            PreparedStatement preparar = conn.prepareStatement(sql);
          ResultSet resultado = preparar.executeQuery();
            while(resultado.next()){
                detallePorId.add(new DetalleCompra(
                 resultado.getInt(1),
                 compraService.finById(resultado.getInt(2)),
                 productoService.finById(resultado.getInt(3)),
                 resultado.getInt(4),
                 resultado.getDouble(5)
               ));
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try{
                if(conn!= null) conn.close();
                if(preparar!= null) preparar.close();
                if(resultado!= null) resultado.close();
            }catch(Exception error){
                error.printStackTrace();
             }
        }
        return null;

     }

    public List<DetalleCompra> Listar() {
        List<DetalleCompra> detalles = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();
        List<Double > subtotals = new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM detalle_compra
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            ResultSet rs = preparar.executeQuery();
            int temp = 1;
            while (rs.next()) {
                productos.add(productoRepository.buscarPorId(rs.getInt("id_producto")));
                cantidades.add(rs.getInt("cantidad"));
                subtotals.add(rs.getDouble("subtotal"));
                if (temp < rs.getInt("id_detalle")) {
                    detalles.add(new DetalleCompra(
                            rs.getInt("id_detalle"),
                          null,
                          //  compraRepository.traerCompraPorId(rs.getInt("id_compra")),
                            productos,
                            cantidades,
                            subtotals));

                    temp = rs.getInt("id_detalle");
                    productos = new ArrayList<>();
                    cantidades = new ArrayList<>();
                }
            }
            return detalles;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
