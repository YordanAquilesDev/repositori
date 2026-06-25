package Dominio.Modelo;

public class Producto {
    private int idProducto;
    private String nombre;
    private String tipoProducto;
    private String unidadMedida;
    private double precio;
    private int stock;

    public Producto(int idProducto,
                    int stock,
                    String unidadMedida,
                    String nombre,
                    String tipoProducto,
                    double precio) {
        this.idProducto = idProducto;
        this.stock = stock;
        this.unidadMedida = unidadMedida;
        this.nombre = nombre;
        this.tipoProducto = tipoProducto;
        this.precio = precio;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioUnidad() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public double getStockActual() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
 
    }
    
    @Override
    public String toString(){
        return nombre;
    }
}
