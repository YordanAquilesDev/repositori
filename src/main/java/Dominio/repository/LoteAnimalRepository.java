package Dominio.repository;

import Dominio.Modelo.LoteAnimal;

public interface LoteAnimalRepository {
 LoteAnimal guardarLoteAnimal(LoteAnimal loteAnimal);
 LoteAnimal  traerPorId(int id);
}
