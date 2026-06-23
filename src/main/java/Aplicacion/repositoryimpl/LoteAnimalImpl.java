package Aplicacion.repositoryimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dominio.Modelo.LoteAnimal;
import Dominio.repository.LoteAnimalRepository;
import Presentacion.Principal.ConexionPostgresSQL;

public class LoteAnimalImpl implements LoteAnimalRepository {
    Connection conexion;
    private final AnimalRepository animalRepository;

    public LoteAnimalImpl() {
        this.animalRepository = new AnimalRepositoryImpl();
        this.conexion = ConexionPostgresSQL.getConexion();
    }

    @Override
    public LoteAnimal guardarLoteAnimal(LoteAnimal loteAnimal) {
        try {
            String sql = """
                    INSERT INTO lote_animal VALUES (?,?,?,?,?,?)
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparar.setInt(1, loteAnimal.getAnimal().getIdAnimal());
            preparar.setDate(2, loteAnimal.getFechaInicio());
            preparar.setInt(3, loteAnimal.getCantidadInicio());
            preparar.setInt(4, loteAnimal.getCantidadActual());
            preparar.setDouble(5, loteAnimal.getPesoPromedio());
            preparar.setString(6, loteAnimal.getEstadoLote());
            ResultSet resultado = preparar.executeQuery();
            resultado.next();
            int idGenerado = resultado.getInt("id_animal");
            loteAnimal.setIdLote(idGenerado);
            return loteAnimal;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LoteAnimal traerPorId(int id) {
        try {
            String sql = """
                    SELECT * FROM lote_animal
                    WHERE id_lote=?
                    """;
            PreparedStatement preparar = conexion.prepareStatement(sql);
            preparar.setInt(1, id);
            ResultSet resultado = preparar.executeQuery();
            resultado.next();
            return new LoteAnimal(
                    resultado.getInt("id_lote"),
                    animalRepository.finById(resultado.getInt("id_animal")),
                    resultado.getDate("fecha_inicio"),
                    resultado.getInt("cantidad_inicio"),
                    resultado.getInt("cantidad_actual"),
                    resultado.getDouble("peso_promedio"),
                    resultado.getString("estado_lote"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
