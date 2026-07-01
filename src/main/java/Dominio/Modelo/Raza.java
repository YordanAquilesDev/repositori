/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio.Modelo;

/**
 *
 * @author user
 */
public class Raza {
    
    private int idRaza;
    private int idEspecie;
    private String nombre;

    public Raza(int idRaza, int idEspecie, String nombre) {
        this.idRaza = idRaza;
        this.idEspecie = idEspecie;
        this.nombre = nombre;
    }

    public Raza() {
    }

    public int getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    




}
