package Dominio.Modelo;

public class Animal {

    private int idAnimal;
    private String especie;
    private String raza;
    List<LoteAnimal> lotes;

    public Animal(int idAnimal, String especie, String raza) {
        this.idAnimal = idAnimal;
        this.especie = especie;
        this.raza = raza;
    }

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
}
