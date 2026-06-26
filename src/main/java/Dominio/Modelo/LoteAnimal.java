package Dominio.Modelo;

import java.sql.Date;

public class LoteAnimal {
    private int idLote;
    private Animal animal;
    private Date fechaInicio;
    private int cantidadInicio;
    private int cantidadActual;
    private double pesoPromedio;
    private String estadoLote;

    public LoteAnimal() {
    }

    public LoteAnimal(int idLote, Animal animal,
                      Date fechaInicio,
                      int cantidadInicio,
                      int cantidadActual,
                      double pesoPromedio,
                      String estadoLote) {
        this.idLote = idLote;
        this.animal = animal;
        this.fechaInicio = fechaInicio;
        this.cantidadInicio = cantidadInicio;
        this.cantidadActual = cantidadActual;
        this.pesoPromedio = pesoPromedio;
        this.estadoLote = estadoLote;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getCantidadInicio() {
        return cantidadInicio;
    }

    public void setCantidadInicio(int cantidadInicio) {
        this.cantidadInicio = cantidadInicio;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public double getPesoPromedio() {
        return pesoPromedio;
    }

    public void setPesoPromedio(double pesoPromedio) {
        this.pesoPromedio = pesoPromedio;
    }

    public String getEstadoLote() {
        return estadoLote;
    }

    public void setEstadoLote(String estadoLote) {
        this.estadoLote = estadoLote;
    }
}
