package Aplicacion.repositoryimpl;

import Dominio.Modelo.Cliente;
import Presentacion.Principal.ConexionPostgresSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository {

    @Override
    public int save(Cliente cliente) {
        Connection conexion=null;
        PreparedStatement preparar=null;
        int resultado=-1;

        try{
            String sql = """
                    INSERT INTO cliente
                        (nombre,apellido,dni,celular,direccion)
                    VALUES 
                                            (?,?,?,?,?) RETURNING id_cliente;
                    """;
            conexion = ConexionPostgresSQL.getConexion();
            preparar = conexion.prepareStatement(sql);
            preparar.setString(1, cliente.getNombre());
            preparar.setString(2, cliente.getApellido());
            preparar.setString(3,cliente.getDni());
            preparar.setString(4, cliente.getCelular());
            preparar.setString(5,cliente.getDireccion());
            resultado = preparar.executeUpdate();

            return resultado;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(conexion!=null) conexion.close();
                if(preparar!=null) preparar.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return resultado;
    }

    @Override
    public Cliente finById(Integer id) {
        Connection conexion=null;
        PreparedStatement preparar=null;
        ResultSet  resultado=null;
        try{
            String sql= """
                    SELECT * FROM cliente WHERE id_cliente = ?;
                    """;
            conexion = ConexionPostgresSQL.getConexion();
            preparar= conexion.prepareStatement(sql);
            preparar.setInt(1, id);
            resultado = preparar.executeQuery();
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
        }finally{
            try{
                if(conexion!=null) conexion.close();
                if(preparar!=null) preparar.close();
                if(resultado!=null) resultado.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public int delete(Cliente cliente) {
        Connection conexion=null;
        PreparedStatement preparar=null;

        int resultado=-1;
        try{
            String sql = """
                    DELETE FROM cliente WHERE id_cliente = ?;
            """;
            conexion = ConexionPostgresSQL.getConexion();
            preparar=conexion.prepareStatement(sql);
            preparar.setInt(1,cliente.getIdCliente());
            resultado=preparar.executeUpdate();
            return resultado;
        } catch (SQLException e) {
            return resultado;

        }finally{
            try{
                if(conexion!=null) conexion.close();
                if(preparar!=null) preparar.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }

    @Override
    public int update(Cliente cliente) {
        Connection conexion=null;
        PreparedStatement preparar=null;
        int resultado=-1;
        try{
            String sql = """
                    UPDATE cliente
                    SET nombre = ?, apellido = ?, direccion = ?, celular = ?, dni = ?
                    WHERE id_cliente = ?;
            """;
            conexion = ConexionPostgresSQL.getConexion();
            preparar=conexion.prepareStatement(sql);
            preparar.setString(1,cliente.getNombre());
            preparar.setString(2,cliente.getApellido());
            preparar.setString(3,cliente.getDireccion());
            preparar.setString(4,cliente.getCelular());
            preparar.setString(5,cliente.getDni());
            preparar.setInt(6,cliente.getIdCliente());
            resultado=preparar.executeUpdate();
            return resultado;
        } catch (SQLException e) {
            return resultado;

        }finally{
            try{
                if(conexion!=null) conexion.close();
                if(preparar!=null) preparar.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public List<Cliente> finAll() {
      List<Cliente> clientes = new ArrayList <>()  ;
      Connection conexion=null;
      PreparedStatement preparar=null;
      ResultSet resultado=null;
       try{
            String sql = """
                    SELECT * FROM cliente;
                    
                    
            """;
            preparar =conexion.prepareStatement(sql);
            resultado = preparar.executeQuery();
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
        }finally{
           try{
               if(conexion!=null) conexion.close();
               if(preparar!=null) preparar.close();
               if(resultado!=null) resultado.close();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }

       }

    }
}
