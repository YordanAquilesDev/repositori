package Aplicacion.repositoryimpl;

import Dominio.Modelo.Animal;
import Dominio.Modelo.Cliente;
import Dominio.repository.ClienteRepository;
import Presentacion.Principal.ConexionPostgresSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository {
    Connection conexion;
    public ClienteRepositoryImpl() {
 
        this.conexion= ConexionPostgresSQL.getConexion();

    }
    @Override
    public Cliente guardar(Cliente cliente) {
        try{
            String sql = """
                    INSERT INTO cliente
                        (nombre,apellido,dni,celular,direccion)
                    VALUES 
                                            (?,?,?,?,?) RETURNING id_cliente;
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setString(1, cliente.getNombre());
            preparar.setString(2, cliente.getApellido());
            preparar.setString(3,cliente.getDni());
            preparar.setString(4, cliente.getCelular());
            preparar.setString(5,cliente.getDireccion());

            ResultSet resultado = preparar.executeQuery();
            resultado.next();
            int idGenerado= resultado.getInt("id_cliente");
            cliente.setIdCliente(idGenerado);
            return cliente;


        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cliente traerPorId(Integer id) {
        try{
            String sql= """
                    SELECT * FROM cliente WHERE id_cliente = ?;
                    """;
            PreparedStatement preparar= conexion.prepareStatement(sql);
            preparar.setInt(1, id);
            ResultSet resultado = preparar.executeQuery();
            resultado.next();
            return  new Cliente(
                    resultado.getInt("id_cliente"),
                    resultado.getString("nombre"),
                    resultado.getString("apellido"),
                    resultado.getString("celular"),
                    resultado.getString("dni"),
                    resultado.getString("direccion")
            );


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void borrarCliente(Cliente cliente) {

    }

    @Override
    public List<Cliente> listarClientes() {
      List<Cliente> clientes = new ArrayList <>()  ;
       try{
            String sql = """
                    SELECT * FROM cliente;
                    
                    
            """;
            PreparedStatement preparar =conexion.prepareStatement(sql);
            ResultSet resultado = preparar.executeQuery();
            while(resultado.next()){
                clientes.add(new Cliente(
                        resultado.getInt("id_cliente"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("dni"),
                        resultado.getString("celular"),
                        resultado.getString("direccion")
                ));

            }
            return clientes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
