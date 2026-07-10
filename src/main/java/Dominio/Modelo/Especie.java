/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio.Modelo;

/**
 *
 * @author user
 */
public class Especie {


    private int idEspecie;
    private String nombre;

    public Especie(int idEspecie, String nombre) {
        this.idEspecie = idEspecie;
        this.nombre = nombre;
    }

    public Especie() {
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
