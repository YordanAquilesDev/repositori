package Aplicacion.repositoryimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Dominio.Modelo.Venta;
import Dominio.repository.CrudGenerico;
import Aplicacion.utils.ConexionMySQL;

public class VentaRepositoryImpl implements CrudGenerico<Venta, Integer> {
    private final UsuarioRepositoryImpl usuarioRepository;

    public VentaRepositoryImpl() {
        this.usuarioRepository = new UsuarioRepositoryImpl();
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
            pstmt.setInt(1,beans.getUsuario().getIdUsuario());
            pstmt.setDate(2,beans.getFecha()); // fecha
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
            pstmt.setInt(1,beans.getUsuario().getIdUsuario());
            pstmt.setDate(2,beans.getFecha());
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
               return Optional.of(new Venta(
                        rs.getInt(1),
                        usuarioRepository.findById(rs.getInt(2)).orElse(null),
                       rs.getDate(3),
                       rs.getDouble(4)
               ));
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
                        rs.getInt(1),
                        usuarioRepository.findById(rs.getInt(2)).orElse(null),
                        rs.getDate(3),
                        rs.getDouble(4)
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
            pstmt.setInt(1,beans.getUsuario().getIdUsuario());
            pstmt.setDate(2,beans.getFecha()); // fecha
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

