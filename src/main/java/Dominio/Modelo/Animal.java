package Dominio.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    
    private int idAnimal;
    private int idRaza;
    private String nombre;
    private String sexo;
    private int edad;
    private double precio;
    private int stock;
    private String estado;

    public Animal(int idAnimal, int idRaza, String nombre, String sexo, int edad, double precio, int stock, String estado) {
        this.idAnimal = idAnimal;
        this.idRaza = idRaza;
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }

    public Animal() {
    }
    

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
