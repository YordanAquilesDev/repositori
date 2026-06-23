package Aplicacion.repositoryimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.Modelo.Cliente;
import Dominio.Modelo.Venta;
import Dominio.repository.CrudGenerico;

//@Repository                                JpaRepository<T, ID>
public class VentaRepositoryImpl implements CrudGenerico<Venta, Integer> {
  private ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
    @Override
    public int save(Venta beans) {
        Connection conn= null;
        PreparedStatement pstmt = null;
        int respuesta = -1;
        try{
            String sql= """
                    INSERT INTO ventas VALUES(?,?,?)
                    """;
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,beans.getCliente().getIdCliente());
            pstmt.setDate(2,beans.getFecha());
            pstmt.setDouble(3,beans.getTotal());
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
            String sql= """
                    UPDATE  venta SET id_cliente = ?,fecha = ?,total = ?
                    """;
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,beans.getCliente().getIdCliente());
            pstmt.setDate(2,beans.getFecha());
            pstmt.setDouble(3,beans.getTotal());
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
            String sql= """
                   DELETE FROM  ventas WHERE id_cliente = ?;
                    """;
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
    public Venta findById(Integer id) {
        Connection conn= null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            String sql= """
                    SELECT * FROM  ventas WHERE id_cliente = ?;
                    """;
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
           rs=pstmt.executeQuery();
           if(rs.next()){
               return new Venta(
                       rs.getInt(1),
                       clienteRepository.finById(rs.getInt(2)),
                       rs.getDate(3),
                       rs.getDouble(4)
               );
           }
           return null;
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
            String sql= """
                    SELECT * FROM  venta ;
                    """;
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
           while(rs.next()){
               list.add( new Venta(
                       rs.getInt(1),
                       clienteRepository.finById(rs.getInt(2)),
                       rs.getDate(3),
                       rs.getDouble(4)
               )
               );

           }
            return null;
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
}

