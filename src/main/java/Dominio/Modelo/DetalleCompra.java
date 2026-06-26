package Dominio.Modelo;

public class DetalleCompra {
    private int idDetalle;
    private Compra compra;
    private Producto producto;
    private double cantidad;
    private double subtotal;

    public DetalleCompra() {
    }

    public DetalleCompra(int idDetalle, Compra compra, Producto producto, double cantidad, double subtotal) {
        this.idDetalle = idDetalle;
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
