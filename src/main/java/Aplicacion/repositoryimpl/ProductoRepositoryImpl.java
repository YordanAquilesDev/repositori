package Aplicacion.repositoryimpl;

import Dominio.Modelo.Producto;
import Dominio.repository.ProductoRepository;
import Presentacion.Principal.ConexionPostgresSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements ProductoRepository {
    Connection conexion;
    public ProductoRepositoryImpl() {
        this.conexion = ConexionPostgresSQL.getConexion();
    }
    @Override
    public Producto buscarPorId(long id) {
        try{
            String sql = """
                    SELECT * FROM producto WHERE id_producto = ?;
                    
                    """;
            PreparedStatement preparar= conexion.prepareStatement(sql);
            preparar.setLong(1, id);
            ResultSet resultado = preparar.executeQuery();
            resultado.next();
            return new Producto(
                    resultado.getInt("id_producto"),
                    resultado.getInt("stock_actual"),
                    resultado.getString("unidad_medida"),
                    resultado.getString("nombre"),
                    resultado.getString("tipo_producto"),
                    resultado.getDouble("precio_unidad")
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public  List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        try{
            String sql= """
                    SELECT * FROM Producto
                    """;
            PreparedStatement preparar=conexion.prepareStatement(sql);
            ResultSet resultado=preparar.executeQuery();
            while(resultado.next()){
                productos.add(new Producto(
                        resultado.getInt("id_producto"),
                        resultado.getInt("stock_actual"),
                        resultado.getString("unidad_medida"),
                        resultado.getString("nombre"),
                        resultado.getString("tipo_producto"),
                        resultado.getDouble("precio_unidad")
                ));
            }
            return productos;

        }catch(Exception e){
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Producto> listaProductosPorAcavar() {
        return List.of();
    }
}
