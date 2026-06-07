package TEST;

import Aplicacion.repositoryimpl.ClienteRepositoryImpl;
import Dominio.Modelo.Cliente;
import Dominio.repository.ClienteRepository;

import java.util.List;

public class ClienteTest {
   static  ClienteRepository clienteRepository = new ClienteRepositoryImpl();
    public static Cliente guardarCliente(){
        return clienteRepository.guardar(new Cliente(0,
                "NOMBRE","APELLIDO","DNI","TELEFONO","DIRECCION"));

    }

    public static Cliente buscarPorId(int id){
        return clienteRepository.traerPorId(id);
    }
    public static List<Cliente> Todos(){
        return clienteRepository.listarClientes();
    }
}
