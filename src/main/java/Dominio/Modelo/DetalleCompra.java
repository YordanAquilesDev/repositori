package Dominio.Modelo;

import java.util.List;

public class DetalleCompra {
    private int idDetalle;
    private Compra compra;
    private List<Producto> productos;
    private List<Integer> cantidad;
    private List<Double> subtotal;
//------

    public DetalleCompra(int idDetalle,
            Compra compra,
            List<Producto> productos,
            List<Integer> cantidad,
            List<Double> subtotal) {
        this.idDetalle = idDetalle;
        this.compra = compra;
        this.productos = productos;
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Integer> getCantidad() {
        return cantidad;
    }

    public void setCantidad(List<Integer> cantidad) {
        this.cantidad = cantidad;
    }

    public List<Double> getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(List<Double> subtotal) {
        this.subtotal = subtotal;
    }
}
