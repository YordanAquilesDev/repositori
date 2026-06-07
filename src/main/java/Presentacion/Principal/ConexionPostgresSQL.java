package Presentacion.Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionPostgresSQL {

    public static Connection getConexion() {
        Connection conexion = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/granjadb";
            conexion = DriverManager.getConnection(
                    url,
                    "postgres","123");

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            //aca se captura el error
            throw new RuntimeException(e);
        }
        return conexion;
    }



}
