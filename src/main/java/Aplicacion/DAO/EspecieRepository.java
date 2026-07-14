/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion.DAO;

import Aplicacion.utils.ConexionMySQL;
import Dominio.Modelo.Animal;
import Dominio.Modelo.Especie;
import Dominio.repository.ICRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author user
 */
public class EspecieRepository implements ICRUD<Especie, Integer>{

    @Override
    public int save(Especie beans) {
        
        Connection conexion = null;
        PreparedStatement ps = null;
        
        int resultado=0;
        try {
            String sql= """
                    INSERT INTO animal VALUES (?)
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
    public int update(Especie beans) {
        Connection conexion = null;
        PreparedStatement ps = null;
        int resultado=-1;
        String sql = """ 
 UPDATE animal SET 
 nombre = ?,
""";
        try  {
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
    public int delete(Integer id) {
        
        Connection conexion = null;
        PreparedStatement ps = null;
        int resultado=-1;
        String sql = "DELETE FROM Especie WHERE nombre = ?";

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
    public Optional<Especie> findById(Integer id) {
        
        Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT * FROM Especie WHERE id = ?";

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, id);

        rs = ps.executeQuery();

        if (rs.next()) {
            Especie especie = new Especie(
                rs.getInt("id"),
                rs.getString("nombre")
            );

            return Optional.of(especie);
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

    return Optional.empty();
    }

    @Override
    public List<Especie> findAll() {
        Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<Especie> list = new ArrayList<>();

    try {
        String sql = "SELECT * FROM Especie";

        conn = ConexionMySQL.getConexion();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            list.add(new Especie(
                    rs.getInt("id"),
                    rs.getString("nombre")
            ));
        }

        return list;

    } catch (SQLException e) {
        throw new RuntimeException(e);

    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

    @Override
    public int saveAndFindId(Especie beans) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        int idGeneradoPorLaBaseDeDatos=0;
        try {
            String sql= """
                    INSERT INTO animal VALUES (?)
                    """;
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(2, beans.getNombre());
            rs.next();
            idGeneradoPorLaBaseDeDatos = rs.getInt(1);
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
    
}
