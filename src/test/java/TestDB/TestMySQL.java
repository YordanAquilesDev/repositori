package TestDB;

import Presentacion.Principal.ConexionMySQL;
import java.sql.Connection;

public class TestMySQL {
    public static void main(String[] args) {
        Connection cn = ConexionMySQL.getConexionMySQL();

        if (cn != null) {
            System.out.println("Conexión exitosa");
        } else {
            System.out.println("No se pudo conectar");
        }
    }
}
