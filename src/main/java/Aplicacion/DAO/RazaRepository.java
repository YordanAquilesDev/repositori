/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion.DAO;

import Aplicacion.utils.ConexionMySQL;
import Dominio.Modelo.Raza;
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
public class RazaRepository implements ICRUD<Raza, Integer>{

    @Override
    public int save(Raza beans) {
        
        Connection conexion = null;
        PreparedStatement ps = null;
        
        int resultado=0;
        try {
            String sql= """
                     INSERT INTO Raza(idEspecie, nombre)
                                    VALUES (?, ?)
                    """;
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, beans.getIdEspecie());
            ps.setString(2, beans.getNombre());
 
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
    public int update(Raza beans) {
        Connection conexion = null;
    PreparedStatement ps = null;

    int resultado = 0;

    try {
        String sql = """
                UPDATE Raza
                SET idEspecie = ?, nombre = ?
                WHERE idRaza = ?
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        ps.setInt(1, beans.getIdEspecie());
        ps.setString(2, beans.getNombre());
        ps.setInt(3, beans.getIdRaza());

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
                DELETE FROM Raza
                WHERE idRaza = ?
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
    public Optional<Raza> findById(Integer id) {
        Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {

        String sql = """
                SELECT *
                FROM Raza
                WHERE idRaza = ?
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        ps.setInt(1, id);

        rs = ps.executeQuery();

        if (rs.next()) {

            Raza raza = new Raza();

            raza.setIdRaza(rs.getInt("idRaza"));
            raza.setIdEspecie(rs.getInt("idEspecie"));
            raza.setNombre(rs.getString("nombre"));

            return Optional.of(raza);
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
    public List<Raza> findAll() {
        Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    List<Raza> lista = new ArrayList<>();

    try {

        String sql = """
                SELECT *
                FROM Raza
                """;

        conexion = ConexionMySQL.getConexion();
        ps = conexion.prepareStatement(sql);

        rs = ps.executeQuery();

        while (rs.next()) {

            Raza raza = new Raza();

            raza.setIdRaza(rs.getInt("idRaza"));
            raza.setIdEspecie(rs.getInt("idEspecie"));
            raza.setNombre(rs.getString("nombre"));

            lista.add(raza);
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
    public int saveAndFindId(Raza beans) {
         Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {

        String sql = """
                INSERT INTO Raza(idEspecie, nombre)
                VALUES(?, ?)
                """;

        conexion = ConexionMySQL.getConexion();

        ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setInt(1, beans.getIdEspecie());
        ps.setString(2, beans.getNombre());

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
