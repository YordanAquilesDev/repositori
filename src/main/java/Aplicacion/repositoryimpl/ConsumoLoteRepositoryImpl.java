package Aplicacion.repositoryimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.Modelo.Animal;
import Dominio.Modelo.ConsumoLote;
import Dominio.Modelo.Producto;
import Presentacion.Principal.ConexionPostgresSQL;

public class ConsumoLoteRepositoryImpl implements ConsumoLoteRepository {
    Connection conexion;
    private final LoteAnimalRepository loteAnimal;
    private final ProductoRepository producto;

    public ConsumoLoteRepositoryImpl() {
        this.loteAnimal = new LoteAnimalImpl();
        this.producto = new ProductoRepositoryImpl();

        this.conexion = ConexionPostgresSQL.getConexion();

    }

    @Override
    public int  save(ConsumoLote consumo) {
        int respuesta = -1;
        PreparedStatement preparar=null;
        Connection conexion = null;
        try {
            String sql = """
                    INSERT INTO consumo_lote VALUES
                    (?,?,?,?)

                    """;
           preparar = conexion.prepareStatement(sql);
            respuesta = preparar.executeUpdate();
            return  respuesta;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                 if(conexion!=null) conexion.close();
                 if(preparar!=null) preparar.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public List<ConsumoLote> listarConsumoLotes() {
        List<ConsumoLote> consumos = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM consumo_lote;
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            ResultSet resultado = preparar.executeQuery();
            boolean Cambia = false;
            int temp = 1;
            while (resultado.next()) {
                int valorAnterior = resultado.getInt("id_consumo");
                if (valorAnterior > temp) {
                    Cambia = true;
                }
                if (Cambia) {
                    consumos.add(new ConsumoLote(
                            resultado.getInt("id_consumo"),
                            loteAnimal.traerPorId(resultado.getInt("id_lote")),
                            resultado.getInt("cantidad"),
                            productos,
                            resultado.getDate("fecha")

                    ));
                    productos = new ArrayList<>();

                }
                productos.add(producto.buscarPorId(resultado.getInt("id_producto")));
                temp = valorAnterior;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }

    @Override
    public ConsumoLote loteMasConsumidor() {
        List<Producto> productos = new ArrayList<>();
        try {
            String sql = """
                            SELECT  * FROM consumo_lote
                            ORDER BY cantidad
                            LIMIT 1;
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            ResultSet resultado = preparar.executeQuery();

            return new ConsumoLote(
                    resultado.getInt("id_consumo"),
                    loteAnimal.traerPorId(
                            resultado.getInt("id_lote")),
                    resultado.getInt("cantidad"),
                    null,
                    resultado.getDate("fecha"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ConsumoLote obtenerConsumoPorId(Long id) {
        try {
            String sql = """
                    SELECT * FROM consumo_lote
                    WHERE id_consumo= ?
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setLong(1, id);
            ResultSet resultado = preparar.executeQuery();
            if (resultado.next()) {
                return new ConsumoLote(
                        resultado.getInt("id_consumo"),
                        loteAnimal.traerPorId(resultado.getInt("id_lote")),
                        resultado.getInt("cantidad"),
                        null,
                        resultado.getDate("fecha"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<ConsumoLote> obtenerConsumoLotePorAnimal(Animal animal) {
        try {
            String sql = """
                    SELECT * FROM consumo_lote cl
                    JOIN lote_animal la ON cl.id_lote = la.id_lote
                    WHERE la.id_animal = ?
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setInt(1, animal.getIdAnimal());
            ResultSet resultado = preparar.executeQuery();
            List<ConsumoLote> consumos = new ArrayList<>();
            while (resultado.next()) {
                consumos.add(new ConsumoLote(
                        resultado.getInt("id_consumo"),
                        loteAnimal.traerPorId(resultado.getInt("id_lote")),
                        resultado.getInt("cantidad"),
                        null,
                        resultado.getDate("fecha")));
            }
            return consumos;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }
}
