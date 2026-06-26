package Test;

import Aplicacion.ServiceImpl.AnimalServiceImpl;
import Aplicacion.ServiceImpl.ClienteServiceImpl;
import Aplicacion.ServiceImpl.CompraServiceImpl;
import Aplicacion.utils.ConexionMySQL;
import Dominio.Modelo.Animal;
import Dominio.Modelo.Cliente;
import Dominio.Modelo.Compra;

import java.sql.Connection;

public class AplicacionTest {


    public static void main(String[] args) {
     //testearAnimalService();
     //   testearCliente();
        testearCompra();
    }

    public static void  testearAnimalService(){
        AnimalServiceImpl animalService= new AnimalServiceImpl();
        Connection conexion=null;
        try {
           conexion=  ConexionMySQL.getConexionMySQL();
           if(conexion==null){
               System.out.println("No se ha establecido conexion");
           }else{
               System.out.println("Conexion establecida");
           }

            //save
            if (animalService.save(new Animal("Test","Pruvea")) > 0) {
                System.out.println("Animal guardado exitosamente");
            } else {
                System.out.println("Error guardando");
            }
            if (animalService.save(new Animal("Test","Pruvea")) > 0) {
                System.out.println("Animal guardado exitosamente");
            } else {
                System.out.println("Error guardando");
            }
            System.out.println();
            //update
            if (animalService.update(new Animal(1,"NuevaRaza","NuevaEspecie")) > 0) {
                System.out.println("Animal actualizado exitosamente");
            } else {
                System.out.println("Error actualizando");
            }
            System.out.println();
            //delete
            if (animalService.delete(1) > 0) {
                System.out.println("Animal eliminado exitosamente");
            } else {
                System.out.println("Error eliminando");
            }
            System.out.println();
            //finById
            if (animalService.finById(2) != null) {
                System.out.println("animal encontrado exitosamente");
            } else {
                System.out.println("Error encontrando");
            }
            System.out.println();
            //findAll
            animalService.findAll().forEach(System.out::println);
            System.out.println();
            //saveAndFinId
            if (animalService.save(new Animal("NuevaEspecie33","NuevaRaza")) > 0) {
                System.out.println("Animal guardado exitosamente");
            } else {
                System.out.println("Error guardando");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  static void testearCliente(){
        ClienteServiceImpl clienteService= new ClienteServiceImpl();
        Connection conexion=null;
        try {
            conexion=  ConexionMySQL.getConexionMySQL();
            if(conexion==null){
                System.out.println("\t\t\tNo se ha establecido conexion");

            }else{
                System.out.println("\t\t\tConexion establecida");
            }

            //save
            if(clienteService.save(new Cliente(
                    0,"N",
                    "A","D",
                    "C","Dir"))>0){
                System.out.println("\t\t\tCliente guardado exitosamente");

            }else{
                System.out.println("Error guardando");
            }
            //save
            if(clienteService.save(new Cliente(
                    0,"N",
                    "A","D",
                    "C","Dir"))>0){
                System.out.println("\t\t\tCliente guardado exitosamente");

            }else{
                System.out.println("Error guardando");
            }
            //update
            if(clienteService.update(new Cliente(1,"ONN",
                    "ABVKH","DHIj",
                    "C","Dir"))>0){
                System.out.println("\t\t\tCliente actualizado exitosamente");
            }else{
                System.out.println("Error actualizando");
            }

            //delete
            if(clienteService.delete(1)>0){
                System.out.println("\t\t\tCliente eliminado exitosamente");
            }else{
                System.out.println("\t\t\tError eliminando");
            }

            //finById
            if(clienteService.finById(2)!=null){
                System.out.println("\t\t\tCliente buscando exitosamente");
            }else{
                System.out.println("Error buscando");
            }

            //finAll
            if(clienteService.findAll().isEmpty()){
                clienteService.findAll().forEach(System.out::println);
            }else{
                System.out.println("Error listando");
            }

            //saveAndFinId
            if(clienteService.save(new Cliente( 0,"N",
                    "A","D",
                    "C","Dir"))>0){
                System.out.println("\t\t\t Cliente guardado exitosamente");
            }else{
                System.out.println("Error guardando");
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  static void testearCompra(){
        CompraServiceImpl compraService= new CompraServiceImpl();
        Connection conexion=null;
        try {
            conexion=  ConexionMySQL.getConexionMySQL();
            if(conexion==null){
                System.out.println("\t\t\tNo se ha establecido conexion");
            }else{
                System.out.println("\t\t\tConexion establecida");
            }

            //save
            if(compraService.save(new Compra())>0){
                System.out.println("\t\t\tCompra guardado exitosamente");
            }else{
                System.out.println("Error guardando");
            }
            //save
            if(compraService.save(new Compra())>0){
                System.out.println("\t\t\tCliente guardado exitosamente");

            }else{
                System.out.println("Error guardando");
            }
            //update
            if(compraService.update(new Compra())>0){
                System.out.println("\t\t\tCliente actualizado exitosamente");
            }else{
                System.out.println("Error actualizando");
            }

            //delete
            if(compraService.delete(1)>0){
                System.out.println("\t\t\tCliente eliminado exitosamente");
            }else{
                System.out.println("\t\t\tError eliminando");
            }

            //finById
            if(compraService.findById(2).isPresent()){
                System.out.println("\t\t\tCliente buscando exitosamente");
            }else{
                System.out.println("Error buscando");
            }

            //finAll
            if(compraService.findAll().isEmpty()){
                compraService.findAll().forEach(System.out::println);
            }else{
                System.out.println("Error listando");
            }

            //saveAndFinId
            if(compraService.save(new Compra( ))>0){
                System.out.println("\t\t\t Cliente guardado exitosamente");
            }else{
                System.out.println("Error guardando");
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
