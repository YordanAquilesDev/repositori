package Dominio.Modelo;

import java.sql.Date;

public class Compra {
    private int idCompra;
    private Proveedor proveedor;
    private Date fecha;
    private double total;
    private Lis<DetalleCompra> detalles;

    public Compra(int idCompra,
            Date fecha,
            Proveedor proveedor,
            double total) {
            detalles= new ArrayList<>();
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.total = total;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
