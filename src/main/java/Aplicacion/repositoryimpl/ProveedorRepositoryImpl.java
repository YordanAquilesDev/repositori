package Aplicacion.repositoryimpl;

import Dominio.Modelo.Proveedor;
import Presentacion.Principal.ConexionPostgresSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProveedorRepositoryImpl implements ProveedorRepository {
   Connection c;
    public ProveedorRepositoryImpl() {
        this.c= ConexionPostgresSQL.getConexion();
    }

    @Override
    public Proveedor guardar(Proveedor proveedor) {
        return null;
    }

    @Override
    public Proveedor buscarPorId(long id) {
        try {
            String sql = "select * from proveedor where id_proveedor=?";
            PreparedStatement preparar= c.prepareStatement(sql);
            preparar.setLong(1,id);
            ResultSet resultado=preparar.executeQuery();
            resultado.next();
            return new Proveedor(
                    resultado.getInt("id_proveedor"),
                    resultado.getString("nombre"),
                    resultado.getString("apellido"),
                    resultado.getString("dni"),
                    resultado.getString("telefono")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Proveedor Actualizar(Proveedor proveedor) {
        return null;
    }

    @Override
    public Proveedor Eliminar(long id) {
        return null;
    }

    @Override
    public List<Proveedor> listarProveedores() {
        return List.of();
    }
}
