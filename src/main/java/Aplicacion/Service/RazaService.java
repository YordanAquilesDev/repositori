/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion.Service;

import Dominio.Modelo.Raza;
import Dominio.repository.ICRUD;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author user
 */
public class RazaService implements ICRUD<Raza,Integer>{

    @Override
    public int save(Raza beans) {
        return 0;
    }

    @Override
    public int update(Raza beans) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public Optional<Raza> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Raza> findAll() {
        return List.of();
    }

    @Override
    public int saveAndFindId(Raza beans) {
        return 0;
    }
}
