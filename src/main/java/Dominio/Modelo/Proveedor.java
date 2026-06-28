package Dominio.Modelo;

public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;

    public Proveedor(int idProveedor, String apellido, String nombre, String dni, String telefono) {

        this.idProveedor = idProveedor;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
    }
    public Proveedor() {

    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    @Override
    public String toString() {
        return  idProveedor + " " + apellido + " " + dni + " " + telefono;
    }
}
