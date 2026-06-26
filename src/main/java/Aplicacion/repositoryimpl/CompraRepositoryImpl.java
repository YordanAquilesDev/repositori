package Aplicacion.repositoryimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Aplicacion.ServiceImpl.ProveedorServiceImpl;
import Dominio.Modelo.Compra;
import Dominio.repository.CrudGenerico;
import Presentacion.Principal.ConexionMySQL;

public class CompraRepositoryImpl implements CrudGenerico<Compra,Integer> {
    private final ProveedorServiceImpl proveedorService;

    public CompraRepositoryImpl() {
        this.proveedorService = new ProveedorServiceImpl();
    }

    @Override
    public int save(Compra compra) {
        int resultado = -1;
        Connection conexion = null;
        PreparedStatement  preparar = null;
        int total = 0;
        try {
            String sql = """
                    INSERT INTO compra(id_proveedor,fecha,total)VALUES
                    (?,?,?) RETURNING *;
                    """;
            conexion = ConexionMySQL.getConexionMySQL();
           preparar = conexion.prepareStatement(sql);
            preparar.setInt(1, compra.getProveedor().getIdProveedor());

            preparar.setDate(2, compra.getFecha());

            preparar.setDouble(3, compra.getTotal());

          return preparar.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            try{
                 if(conexion!=null) conexion.close();
                 if(preparar!=null) preparar.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int update(Compra beans) {
            int resultado = -1;
            Connection conexion = null;
            PreparedStatement  preparar = null;
            try {
                String sql = """
                    UPDATE compra SET id_proveedor=?,fecha=?,total=? WHERE id_compra=?;
                    """;
                conexion = ConexionMySQL.getConexionMySQL();
                preparar = conexion.prepareStatement(sql);
                preparar.setInt(1, beans.getProveedor().getIdProveedor());

                preparar.setDate(2, beans.getFecha());

                preparar.setDouble(3, beans.getTotal());
                preparar.setInt(4,beans.getIdCompra());

                return preparar.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally{
                try{
                    if(conexion!=null) conexion.close();
                    if(preparar!=null) preparar.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
    }

    @Override
    public int delete(Integer id) {
            Connection conexion = null;
            PreparedStatement  preparar = null;

            try {
                String sql = """
                    DELETE FROM compra WHERE id_compra=?;
                    """;
                conexion = ConexionMySQL.getConexionMySQL();
                preparar = conexion.prepareStatement(sql);
                preparar.setInt(1,id);

                return preparar.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally{
                try{
                    if(conexion!=null) conexion.close();
                    if(preparar!=null) preparar.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
    }

    @Override
    public List<Compra> finAll() {
        List<Compra> compras = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement  preparar = null;
        try {
            String sql = """
                    SELECT * FROM compra
                    """;
           conexion = ConexionMySQL.getConexionMySQL();
             preparar = conexion.prepareStatement(sql);
            ResultSet resultado = preparar.executeQuery();
            while (resultado.next()) {
                compras.add(new Compra(
                        resultado.getInt("id_compra"),
                        resultado.getDate("fecha"),
                        proveedorService.finById(resultado.getInt("id_proveedor")).orElse(null),
                        resultado.getDouble("total")));
            }
            return compras;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            try{
                if(conexion!=null) conexion.close();
                if(preparar!=null) preparar.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int saveAndFinId(Compra beans) {
        Connection conn= null;
        PreparedStatement pstmt = null;
        int idGenerado = 0;
        try{
            String sql= """
                
                    --                          |              |           |
                    --                          |              |           |
                    INSERT INTO compra VALUES( ?       ,      ?       ,    ? )  RETURNING id_compra;
                    """;
            pstmt=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,beans.getProveedor().getIdProveedor());// id_proveedor
            pstmt.setDate(2,beans.getFecha()); // fecha
            pstmt.setDouble(3,beans.getTotal());// total

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


    public List<Compra> listarComprasMasAltos() {
        List<Compra> comprasMasAltos = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement  preparar = null;
        try {
            String sql = """
                            SELECT  * FROM compra
                            ORDER BY total DESC
                            LIMIT 5;
                    """;
            conexion = ConexionMySQL.getConexionMySQL();
            preparar = conexion.prepareStatement(sql);
            ResultSet resultado = preparar.executeQuery();
            while (resultado.next()) {
                comprasMasAltos.add(new Compra(
                        resultado.getInt("id_compra"),
                        resultado.getDate("fecha"),
                        proveedorService.finById(resultado.getInt("id_proveedor")).orElse(null),
                        resultado.getDouble("total")));
            }
            return comprasMasAltos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            try{
                if(conexion!=null) conexion.close();
                if(preparar!=null) preparar.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public List<Compra> listarComprasPorFecha(Date fecha, Date fecha2) {
        List<Compra> compras = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement  preparar = null;
        try {
            String sql = """
                            SELECT  * FROM compra
                           WHERE fecha BETWEEN ? AND ?
                    """;
            conexion = ConexionMySQL.getConexionMySQL();
          preparar = conexion.prepareStatement(sql);
            preparar.setDate(1, fecha);
            preparar.setDate(2, fecha2);
            ResultSet resultado = preparar.executeQuery();
            while (resultado.next()) {
                compras.add(new Compra(
                        resultado.getInt("id_compra"),
                        resultado.getDate("fecha"),
                        proveedorService.finById(resultado.getInt("id_proveedor")).orElse(null),
                        resultado.getDouble("total")

                ));
            }
            return compras;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            try{
                if(conexion!=null) conexion.close();
                if(preparar!=null) preparar.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Optional<Compra> finById(Integer id) {
        Connection conexion = null;
        PreparedStatement  preparar = null;
        try {
            String sql = """
                            SELECT * FROM compra
                            WHERE id_compra=?
                    """;
            conexion = ConexionMySQL.getConexionMySQL();
             preparar = conexion.prepareStatement(sql);
            preparar.setInt(1, id);
            ResultSet resultado = preparar.executeQuery();
            if (resultado.next()) {
                return Optional.of(new Compra(
                        resultado.getInt("id_compra"),
                        resultado.getDate("fecha"),
                        proveedorService.finById(resultado.getInt("id_proveedor")).orElse(null),
                        resultado.getDouble("total")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            try{
                if(conexion!=null) conexion.close();
                if(preparar!=null) preparar.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }
}
