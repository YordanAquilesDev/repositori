package Aplicacion.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Dominio.Modelo.Venta;
import Dominio.repository.ICRUD;
import Aplicacion.utils.ConexionMySQL;

public class VentaRepository implements ICRUD<Venta, Integer> {
    private final UsuarioRepository usuarioRepository;

    public VentaRepository() {
        this.usuarioRepository = new UsuarioRepository();
    }
    @Override
    public int save(Venta beans) {
        Connection conn= null;
        PreparedStatement pstmt = null;
        int respuesta = -1;
        try{
            String sql= "INSERT INTO venta(id_usuario, fecha, total) VALUES(?, ?, ?)";
            conn= ConexionMySQL.getConexionMySQL();
            pstmt=conn.prepareStatement(sql);
            pstmt.setDouble(3,beans.getTotal());// total
            respuesta = pstmt.executeUpdate();
            return respuesta;
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if(conn!= null) conn.close();
             } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int update(Venta beans) {
        Connection conn= null;
        PreparedStatement pstmt = null;
        int respuesta = -1;
        try{
            String sql= "UPDATE venta SET id_usuario = ?, fecha = ?, total = ? WHERE id_venta = ?";
            conn= ConexionMySQL.getConexionMySQL();
            pstmt=conn.prepareStatement(sql);
            pstmt.setDouble(3,beans.getTotal());
            pstmt.setInt(4,beans.getIdVenta());
            respuesta = pstmt.executeUpdate();
            return respuesta;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if(conn!= null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public int delete(Integer integer) {
        Connection conn= null;
        PreparedStatement pstmt = null;
        int respuesta = -1;
        try{
            String sql= "DELETE FROM venta WHERE id_venta = ?";
            conn= ConexionMySQL.getConexionMySQL();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,integer);
            respuesta = pstmt.executeUpdate();
            return respuesta;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if(conn!= null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Optional<Venta> findById(Integer id) {
        Connection conn= null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn= ConexionMySQL.getConexionMySQL();
            String sql= "SELECT * FROM venta WHERE id_venta = ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
           rs=pstmt.executeQuery();
           if(rs.next()){
               return  null;/*Optional.of(new Venta(
                        rs.getInt(1),
                        usuarioRepository.findById(rs.getInt(2)).orElse(null),
                       rs.getDate(3),
                       rs.getDouble(4)
               ));*/
           }
           return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if(conn!= null) conn.close();
                if(rs != null) rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public List<Venta> findAll() {
        Connection conn= null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Venta> list = new ArrayList<>();
        try{
            String sql= "SELECT * FROM venta";
            conn= ConexionMySQL.getConexionMySQL();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
           while(rs.next()){
               list.add( new Venta(
                )
                );
            }
             return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if(conn!= null) conn.close();
                if(rs != null) rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int saveAndFindId(Venta beans) {
        Connection conn= null;
        PreparedStatement pstmt = null;
        int idGenerado = 0;
        try{
            String sql= "INSERT INTO venta(id_usuario, fecha, total) VALUES(?, ?, ?)";
            conn= ConexionMySQL.getConexionMySQL();
            pstmt=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setDouble(3,beans.getTotal());

            int filaAfectadas = pstmt.executeUpdate();
            if(filaAfectadas>0){
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Extraemos el ID (usualmente es la primera columna del ResultSet obtenido)
                        idGenerado= generatedKeys.getInt(1);

                    }
                }
            }
            return idGenerado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if(conn!= null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

