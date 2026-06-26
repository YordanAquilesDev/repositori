package Dominio.Modelo;

import java.util.Date;

public class Pedido {

    int idPedido;
    private Cliente cliente;
    private Date fecha;
    private String estado;
    double total;

    public Pedido(int id,
            Date fecha,
            Cliente cliente,
            String estado,
            double total) {
        this.fecha = fecha;
        this.idPedido=id;
        this.cliente = cliente;
        this.estado = estado;
        this.total = total;

    }

    public Date getFecha() {
        return fecha;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Pedido() {

    }

    public int getIdCliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
