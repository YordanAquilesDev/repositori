package Aplicacion.DAO;

import Dominio.Modelo.Animal;
import Dominio.repository.ICRUD;
import Aplicacion.utils.ConexionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimalDAO implements ICRUD<Animal, Integer> {

    public AnimalDAO() {
    }

    @Override
    public int save(Animal beans) {
        Connection conexion = null;
        PreparedStatement ps = null;
        int resultado = 0;
        try {
            String sql = """
                    INSERT INTO Animal(idRaza, nombre, sexo, edad, precio, stock, estado)
                    VALUES (?,?,?,?,?,?,?)
                    """;
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, beans.getIdRaza());
            ps.setString(2, beans.getNombre());
            ps.setString(3, beans.getSexo());
            ps.setInt(4, beans.getEdad());
            ps.setDouble(5, beans.getPrecio());
            ps.setInt(6, beans.getStock());
            ps.setString(7, beans.getEstado());
            resultado = ps.executeUpdate();
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int update(Animal beans) {
        Connection conexion = null;
        PreparedStatement ps = null;
        int resultado = -1;
        String sql = """
                UPDATE Animal
                SET idRaza = ?, nombre = ?, sexo = ?, edad = ?, precio = ?, stock = ?, estado = ?
                WHERE idAnimal = ?
                """;
        try {
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, beans.getIdRaza());
            ps.setString(2, beans.getNombre());
            ps.setString(3, beans.getSexo());
            ps.setInt(4, beans.getEdad());
            ps.setDouble(5, beans.getPrecio());
            ps.setInt(6, beans.getStock());
            ps.setString(7, beans.getEstado());
            ps.setInt(8, beans.getIdAnimal());
            resultado = ps.executeUpdate();
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public int delete(Integer id) {
        Connection conexion = null;
        PreparedStatement ps = null;
        int resultado = -1;
        String sql = "DELETE FROM Animal WHERE idAnimal = ?";

        try {
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            resultado = ps.executeUpdate();
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @Override
    public Optional<Animal> findById(Integer id) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Animal WHERE idAnimal = ?";
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Animal(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDouble(6),
                        rs.getInt(7),
                        rs.getString(8)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Animal> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Animal> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Animal";
            conn = ConexionMySQL.getConexion();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Animal(rs.getInt(1), // id genera
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDouble(6),
                        rs.getInt(7),
                        rs.getString(8)
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int saveAndFindId(Animal beans) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idGeneradoPorLaBaseDeDatos = 0;
        try {
            String sql = """
                    INSERT INTO Animal(idRaza, nombre, sexo, edad, precio, stock, estado)
                    VALUES (?,?,?,?,?,?,?)
                    """;
            conexion = ConexionMySQL.getConexion();
            ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, beans.getIdRaza());
            ps.setString(2, beans.getNombre());
            ps.setString(3, beans.getSexo());
            ps.setInt(4, beans.getEdad());
            ps.setDouble(5, beans.getPrecio());
            ps.setInt(6, beans.getStock());
            ps.setString(7, beans.getEstado());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGeneradoPorLaBaseDeDatos = rs.getInt(1);
            }
            return idGeneradoPorLaBaseDeDatos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
