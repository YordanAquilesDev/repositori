package Aplicacion.ImplService;

import Dominio.IService.LogicaUtilParaTraerPorId;
import Dominio.Modelo.Animal;
import Dominio.Modelo.Cliente;
import Dominio.Modelo.Producto;
import Dominio.Modelo.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceLogicaUtilPorIDImpl implements LogicaUtilParaTraerPorId {

    Connection conexion;
    public ServiceLogicaUtilPorIDImpl(Connection conexion) {
        this.conexion = conexion;
    }
    @Override
    public Producto traerProductoPorId(int id) {
        try {
            String sql = "SELECT * FROM Producto WHERE idProducto = ?";

            PreparedStatement prepararParaLaConsultaSql=conexion.prepareStatement(sql);
            prepararParaLaConsultaSql.setInt(1, id);
            ResultSet resultado = prepararParaLaConsultaSql.executeQuery();
            resultado.next();
            return new Producto(
                    resultado.getInt("idProducto"),
                    resultado.getInt("stock"),
                    resultado.getString("unidadmedida"),
                    resultado.getString("nombre"),
                    resultado.getString("tipoProducto"),
                    resultado.getDouble("preciounidad")

            );
        }catch (SQLException e){
            //logica para menejar errores
            /*...*/
            System.err.println("Error en traer Producto: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Cliente traerClientePorId(int id) {
        try{
            String sql = "SELECT * FROM Cliente WHERE idCliente = ?";

            PreparedStatement prepararParaLaConsultaSql=conexion.prepareStatement(sql);
            prepararParaLaConsultaSql.setInt(1, id);
            ResultSet resultado = prepararParaLaConsultaSql.executeQuery();
            resultado.next();
            return new Cliente(
                    resultado.getInt("id_cliente"),
                    resultado.getString("nombre"),
                    resultado.getString("apellido"),
                    resultado.getString("dni"),
                    resultado.getString("direccion"),
                    resultado.getString("celular")

            );
        }catch (SQLException e){
            System.err.println("Error en traer Cliente: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Proveedor traerProveedorPorId(int id) {
        try{
            String sql = "SELECT * FROM Proveedor WHERE idProveedor = ?";

            PreparedStatement prepararParaLaConsultaSql=conexion.prepareStatement(sql);
            prepararParaLaConsultaSql.setInt(1, id);
            ResultSet resultado = prepararParaLaConsultaSql.executeQuery();
            resultado.next();
            return new Proveedor(
                    resultado.getInt("idProveedor"),
                    resultado.getString("apellido"),
                    resultado.getString("nombre"),
                    resultado.getString("dni"),
                    resultado.getString("celular")
            );
        }catch (SQLException e){
            System.err.println("Error en traer Proveedor: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Animal traerAnimalPorId(int id) {
        try{
            String sql = "SELECT * FROM Animal WHERE id_animal = ?";

            PreparedStatement prepararParaLaConsultaSql=conexion.prepareStatement(sql);
            prepararParaLaConsultaSql.setInt(1, id);
            ResultSet resultado = prepararParaLaConsultaSql.executeQuery();
            resultado.next();
            return new Animal(
                    resultado.getInt("id_animal"),
                    resultado.getString("especie"),
                    resultado.getString("raza")

            );
        }catch (SQLException e){
            System.err.println("Error en traer Animal: " + e.getMessage());
        }
        return null;
    }
}
