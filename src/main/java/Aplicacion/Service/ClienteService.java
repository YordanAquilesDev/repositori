/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion.Service;

import Dominio.Modelo.Cliente;
import Dominio.repository.ICRUD;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author user
 */
public class ClienteService implements ICRUD<Cliente,Integer> {

    @Override
    public int save(Cliente beans) {
        if(beans==null){
            return -1;
        }
 return  0;
    }

    @Override
    public int update(Cliente beans) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public Optional<Cliente> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Cliente> findAll() {
        return List.of();
    }

    @Override
    public int saveAndFindId(Cliente beans) {
        return 0;
    }
}
