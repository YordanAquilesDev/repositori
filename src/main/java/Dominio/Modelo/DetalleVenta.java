package Dominio.Modelo;

public class DetalleVenta {

    private int idDetalle;
    private int idVenta;
    private int idAnimal;
    private int cantidad;
    private double precio;
    private double subtotal;

    public DetalleVenta(int idDetalle, int idVenta, int idAnimal, int cantidad, double precio, double subtotal) {
        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.idAnimal = idAnimal;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    public DetalleVenta() {
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    




}
