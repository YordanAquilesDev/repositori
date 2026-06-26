package Dominio.Modelo;

import java.sql.Date;

public class MovimientoAlmacen {
    private int idMovimiento;
    private Producto producto;
    private String tipoMovimiento;
    private double cantidad;
    private Date fecha;
    private String contexto;

    public MovimientoAlmacen() {
    }

    public MovimientoAlmacen(int idMovimiento, Producto producto, String tipoMovimiento, double cantidad, Date fecha, String contexto) {
        this.idMovimiento = idMovimiento;
        this.producto = producto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.contexto = contexto;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }
}