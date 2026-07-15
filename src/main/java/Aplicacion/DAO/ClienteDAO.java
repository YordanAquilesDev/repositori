/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion.DAO;

import Aplicacion.utils.ConexionMySQL;
import Dominio.Modelo.Animal;
import Dominio.Modelo.Cliente;
import Dominio.repository.ICRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author user
 */
public class ClienteDAO implements ICRUD<Cliente,Integer> {

    @Override
    public int save(Cliente beans) {
        Connection conexion = null;
        PreparedStatement ps = null;
        int resultado=0;
        try {
            String sql= """
                    INSERT INTO Cliente(idUsuario, dni, telefono, direccion)
                    VALUES (?,?,?,?)
                    """;
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, beans.getIdUsuario());
            ps.setString(2, beans.getDni());
            ps.setString(3,beans.getTelefono());
            ps.setString(4, beans.getDireccion());
            resultado=ps.executeUpdate();
            return  resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if (ps != null) ps.close();
                if(conexion!= null) conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int update(Cliente beans) {
        Connection conexion = null;
        PreparedStatement ps = null;
        int resultado=-1;

        try  {
            String sql = """
                    UPDATE Cliente
                    SET dni = ?, telefono = ?, direccion = ?
                    WHERE idCliente = ?
                    """;
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, beans.getDni());
            ps.setString(2, beans.getTelefono());
            ps.setString(3, beans.getDireccion());
            ps.setInt(4, beans.getIdCliente());
            resultado=ps.executeUpdate();
            return  resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if (ps != null) ps.close();
                if(conexion!= null) conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
    }

    @Override
    public int delete(Integer id) {
        Connection conexion = null;
        PreparedStatement ps = null;
        int resultado=-1;
        String sql = "DELETE FROM Cliente WHERE idCliente=?;";

        try  {
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            resultado=ps.executeUpdate();
            return  resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if (ps != null) ps.close();
                if(conexion!= null) conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try  {
            String sql= "SELECT * FROM Cliente WHERE idCliente = ?";
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                return Optional.of(new Cliente(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if (ps != null) ps.close();
                if(conexion!= null) conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Cliente> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Cliente> list = new ArrayList<>();
        try {
            String sql= "SELECT * FROM Cliente";
            conn = ConexionMySQL.getConexion();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                list.add(new Cliente(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if (pstmt != null) pstmt.close();
                if(conn!= null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int saveAndFindId(Cliente beans) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        int idGeneradoPorLaBaseDeDatos=0;
        try {
            String sql= """
                    INSERT INTO Cliente(idUsuario, dni, telefono, direccion)
                    VALUES (?,?,?,?)
                    """;
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, beans.getIdUsuario());
            ps.setString(2, beans.getDni());
            ps.setString(3,beans.getTelefono());
            ps.setString(4, beans.getDireccion());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGeneradoPorLaBaseDeDatos = rs.getInt(1);
            }
            return  idGeneradoPorLaBaseDeDatos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if (ps != null) ps.close();
                if(conexion!= null) conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Optional<Cliente> findByUsuarioId(int idUsuario) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Cliente WHERE idUsuario = ?";
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Cliente(
                        rs.getInt("idCliente"),
                        rs.getInt("idUsuario"),
                        rs.getString("dni"),
                        rs.getString("telefono"),
                        rs.getString("direccion")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

}
