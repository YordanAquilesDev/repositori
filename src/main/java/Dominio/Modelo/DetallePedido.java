package Dominio.Modelo;

import java.util.List;

public class DetallePedido {
    private int idDetalle;
    private Pedido pedido;
    private Producto producto;
    private int cantidad;
    private double subTotal;

    public DetallePedido() {
    }

    public DetallePedido(int idDetalle, Pedido pedido, Producto producto, int cantidad, double subTotal) {
        this.idDetalle = idDetalle;
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
