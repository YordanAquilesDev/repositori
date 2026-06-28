package Dominio.Modelo;

import java.util.List;

public class Producto {
    private int idProducto;
    private String nombre;
    private String tipoProducto;
    private String unidadMedida;
    private double precioUnidad;
    private double stockActual;
    List<MovimientoAlmacen> movimientos;

    public Producto(int idProducto,
                    double stockActual,
                    String unidadMedida,
                    String nombre,
                    String tipoProducto,
                    double precioUnidad) {
        this.idProducto = idProducto;
        this.stockActual = stockActual;
        this.unidadMedida = unidadMedida;
        this.nombre = nombre;
        this.tipoProducto = tipoProducto;
        this.precioUnidad = precioUnidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public double getStockActual() {
        return stockActual;
    }

    public void setStockActual(double stockActual) {
        this.stockActual = stockActual;
    }
}
