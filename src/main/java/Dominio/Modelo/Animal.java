package Dominio.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Animal {

    private int idAnimal;
    private String especie;
    private String raza;
    List<LoteAnimal> lotes=new ArrayList<>();

    public Animal(int idAnimal, String especie, String raza) {
        this.idAnimal = idAnimal;
        this.especie = especie;
        this.raza = raza;
    }

    // bgougoihpiohjnpolñjb giutgyiouyhopijp
    public Animal(String especie, String raza) {
        this.especie = especie;
        this.raza = raza;
    }
    public Animal() {

    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "raza='" + raza + '\'' +
                ", especie='" + especie + '\'' +
                '}';
    }
}
