/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion.DAO;

import Aplicacion.utils.ConexionMySQL;
import Dominio.Modelo.Rol;
import Dominio.repository.ICRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author user
 */
public class RolRepository implements ICRUD<Rol, Integer>{

    @Override
    public int save(Rol beans) {
        Connection conexion = null;
        PreparedStatement ps = null;
        
        int resultado=0;
        try {
            String sql= """
                      INSERT INTO Rol(nombre)
                                     VALUES(?)
                    """;
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, beans.getNombre());
 
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
    public int update(Rol beans) {
        Connection conexion = null;
    PreparedStatement ps = null;

    int resultado = 0;

    try {

        String sql = """
                UPDATE Rol
                SET nombre = ?
                WHERE idRol = ?
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        ps.setString(1, beans.getNombre());
        ps.setInt(2, beans.getIdRol());

        resultado = ps.executeUpdate();

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

    return resultado;
    }

    @Override
    public int delete(Integer id) {
        Connection conexion = null;
    PreparedStatement ps = null;

    int resultado = 0;

    try {

        String sql = """
                DELETE FROM Rol
                WHERE idRol = ?
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        ps.setInt(1, id);

        resultado = ps.executeUpdate();

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

    return resultado;
    }

    @Override
    public Optional<Rol> findById(Integer id) {
        Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {

        String sql = """
                SELECT *
                FROM Rol
                WHERE idRol = ?
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        ps.setInt(1, id);

        rs = ps.executeQuery();

        if (rs.next()) {

            Rol rol = new Rol();

            rol.setIdRol(rs.getInt("idRol"));
            rol.setNombre(rs.getString("nombre"));

            return Optional.of(rol);
        }

        return Optional.empty();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

    @Override
    public List<Rol> findAll() {
        Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    List<Rol> lista = new ArrayList<>();

    try {

        String sql = """
                SELECT *
                FROM Rol
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        rs = ps.executeQuery();

        while (rs.next()) {

            Rol rol = new Rol();

            rol.setIdRol(rs.getInt("idRol"));
            rol.setNombre(rs.getString("nombre"));

            lista.add(rol);
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    return lista;
    }

    @Override
    public int saveAndFindId(Rol beans) {
         Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {

        String sql = """
                INSERT INTO Rol(nombre)
                VALUES(?)
                """;

        conexion = ConexionMySQL.getConexion();

        ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setString(1, beans.getNombre());

        ps.executeUpdate();

        rs = ps.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
    
}
