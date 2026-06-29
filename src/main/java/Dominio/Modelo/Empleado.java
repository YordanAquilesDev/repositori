package Dominio.Modelo;

public class Empleado {

    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String password;
    private String rol;
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {}

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuatio) {
        this.nombreUsuario = nombreUsuatio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
