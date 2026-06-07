package Dominio.Modelo;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private String celular;
    private String direccion;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Cliente(int id, String nombre, String apellido,
                   String dni, String telefono,String direccion) {
        this.idCliente = id;
        this.nombre = nombre;
        this.apellido = apellido;
        celular = telefono;
        this.dni = dni;
        this.direccion = direccion;
    }

    public Cliente() {

    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCelular() {
        return celular;
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


}