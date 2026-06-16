package Dominio.repository;

import Dominio.Modelo.LoteAnimal;

public interface LoteAnimalRepository {
 //CRUD de la tabla lote_animal
 LoteAnimal guardarLoteAnimal(LoteAnimal loteAnimal);
 LoteAnimal  traerPorId(int id);
}
